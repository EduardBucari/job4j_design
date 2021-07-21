package ru.job4j.io.zipSecond;

import ru.job4j.io.zipSecond.ArgsNameSecond;

public class ArgZipSecond {
    private final ArgsNameSecond parser;
    private final StringBuilder error;

    public ArgZipSecond(String[] args) {
        parser = ArgsNameSecond.of(args);
        error = new StringBuilder();
    }

    public boolean valid() {
        try {
            directory();
        } catch (IllegalArgumentException e) {
            error.append(e.getMessage());
            error.append(System.lineSeparator());
        }
        try {
            exclude();
        } catch (IllegalArgumentException e) {
            error.append(e.getMessage());
            error.append(System.lineSeparator());
        }
        try {
            output();
        } catch (IllegalArgumentException e) {
            error.append(e.getMessage());
            error.append(System.lineSeparator());
        }
        if (error.length() != 0) {
            throw new IllegalArgumentException(error.toString());
        }
        return true;
    }

    public String directory() {
        return parser.get("d");
    }

    public String exclude() {
        return parser.get("e");
    }

    public String output() {
        return parser.get("o");
    }
}
