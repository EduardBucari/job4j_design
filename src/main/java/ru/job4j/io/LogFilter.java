package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * BufferedReader.
 * Задание:
 * 1. Создайте класс ru.job4j.io.LogFilter.
 *    Создайте файл log.txt. Запишите в него текст.
 * 2. Метод filter должен прочитать файл и вернуть строки,
 *    где предпоследнее число - это 404.
 */
public class LogFilter {
    /**
     * Метод filter применяет буферизированный поток и фильтрует лог файл.
     * @param file имя лог файла.
     * @return список отфильтрованных строк.
     */
    public static List<String> filter(String file) {
        List<String> result = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            in.lines()
                    .filter(e -> e.contains("404"))
                    .forEach(result :: add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        System.out.println(log);
    }



















}
