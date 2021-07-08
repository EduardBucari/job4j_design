package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Анализ доступности сервера.
 * (преобразования одного файла в другой.)
 *
 * Для решения задачи будем использовать метод split
 *   String[] words = str.split(" ");
 *
 * Задание.
 * 1. Реализуйте метод unavailable().
 *    source - путь к файлу лога. target - имя путь к файлу результата анализа.
 * 2. Метод unavailable должен находить диапазоны, когда сервер не работал.
 *    Сервер не работал, если status = 400 или 500.
 *    Диапазоном считается последовательность статусов 400 и 500
 *    Начальное время - это время когда статус 400 или 500.
 *    Конечное время это когда статус меняется с 400 или 500 на 200 или 300.
 * 3. Результат анализа нужно записать в файл target. (Формат файла; начала периода; конец периода);
 */
public class Analiz {
    private static List<String> tmpList = new ArrayList<>();
    private static List<String> resultList = new ArrayList<>();

    public static void main(String[] args) {
        unavailable();
    }

   private static void unavailable() {
       toList();
       String start = null;
       String end = null;
       String resultStr = null;
       for (String line : tmpList) {
           if ((line.contains("400") || line.contains("500")) && start == null) {
               start = line.substring(4, line.length());
           }
           if ((line.contains("200") || line.contains("300")) && (start != null && end == null)) {
               end = line.substring(4, line.length());
               resultStr = start + ";" + end;
               start = null;
               end = null;
               resultList.add(resultStr);
           }
       }
       toFile();
   }

   private static void toList() {
        try (BufferedReader in = new BufferedReader(
                new FileReader("./data/source.txt"))) {
        in.lines().forEach(tmpList :: add);
        } catch (Exception e) {
            e.printStackTrace();
        }
   }

   private static void toFile() {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream("./data/target.txt")))) {
            resultList.forEach(value -> out.write(value + System.lineSeparator()));
        } catch (Exception e) {
            e.printStackTrace();
        }
   }
}
