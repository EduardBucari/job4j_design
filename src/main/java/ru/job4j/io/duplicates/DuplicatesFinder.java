package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Класс DuplicatesFinder - точка входа в программу для поиска дубликатов файлов.
 */
public class DuplicatesFinder {

    private static void usage() {
        System.out.println("Использование: duplicatesFinder <path>");
        System.out.println("Пример: java -jar duplicatesFinder.jar c:\\projects\\job4j_design");
        System.exit(-1);
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            usage();
        }
        Files.walkFileTree(Path.of(args[0]), new DuplicatesVisitor());
    }
}
