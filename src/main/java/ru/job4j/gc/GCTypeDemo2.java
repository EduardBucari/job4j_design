package ru.job4j.gc;

import java.util.HashMap;
import java.util.Map;

public class GCTypeDemo2 {
    private static Map<String, String> sContainer = new HashMap<>();

    public static void main(String[] args) {
        System.out.println("Start of program!");
        String stringWithPrefix = "stringWithPrefix";

        for (int i = 0; i < 3000000; i++) {
            String newString = stringWithPrefix + i;
            sContainer.put(newString, newString);
        }
        System.out.println("Map size: " + sContainer.size());
        System.gc();

        for (int i = 0; i < 2000000; i++) {
            String newString = stringWithPrefix + i;
            sContainer.remove(newString);
        }

        System.out.println("Map size: " + sContainer.size());
        System.out.println("End of program!");
    }
}
