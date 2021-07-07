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


    /**
     * BufferedOutputStream
     * Задание 2.
     * 1. Доработайте класс ru.job4j.io.LogFilter.
     * 2. Нужно добавить метод save(String log). Метод должен записывать результат фильтрации в файл.
     * т.е. Метод save получает данные лог файла на вход и записывает в файл.
     * @param log - записываемые данные.
     * @param file - имя файла.
     */
    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)
                )
        )) {
            for (String s : log) {
                out.println(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        save(log, "404.txt");
    }
}
