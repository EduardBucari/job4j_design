package ru.job4j.io.search;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        return values.get(key);
    }

    public int getLength() {
        return values.size();
    }

    private void parse(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("Right format: java -jar ScriptName -d=directory -n=filename -t=typeSearch -o=result");
        }
        for (String arg : args) {
            String[] buffArg = arg.split("=");
            if (buffArg.length < 2) {
                throw new IllegalArgumentException("Right format: '-key=value'");
            }
            values.put(buffArg[0].substring(1), buffArg[1]);
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }
}
