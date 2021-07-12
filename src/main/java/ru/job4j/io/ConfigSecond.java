package ru.job4j.io;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class ConfigSecond {
    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public ConfigSecond(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String nullCheck;
            do {
                String buff = br.readLine();
                nullCheck = buff;
                if (buff != null && !buff.contains("#") && !buff.isEmpty()) {
                    String[] buffArr = buff.split("=");
                    if (buffArr.length < 2) {
                        throw new IllegalArgumentException();
                    }
                    values.put(buffArr[0], buffArr[1]);
                }
            } while (nullCheck != null);
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out :: add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }
}
