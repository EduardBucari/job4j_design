package ru.job4j.io;

import java.io.FileInputStream;

/**
 * FileInputStream.
 * В этом уроке узнаем, как в Java прочитать файл.
 * java.io.FileInputStream - Этот класс позволяет прочитать данные из файла.
 *
 * Данные считываются по байтам.
 * Здесь используется конструкция try-with-resources, чтобы закрыть поток ввода.
 *
 * Задание:
 * 1.Создайте в корне проекта файл even.txt.
 * 2.В файл добавьте числа. Одно число  на строку.(1 5 15 17)
 * 3. Создайте класс ru.job4j.io.EvenNumberFile с методом main.
 * 4. В классе нужно прочитать файл even.txt.
 * Для каждого числа проверить является ли оно четным числом.
 * Ответ вывести на консоль.
 *
 * Четное число - число, которое делится на два без остатка.
 * int num = 10;
 * boolean rsl = num % 2 == 0; //истина, если четное.
 */
public class EvenNumberFile {
    public static void main(String[] args) {
        StringBuilder text = new StringBuilder();
        try (FileInputStream in = new FileInputStream("even.txt")) {
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] lines = text.toString().split(System.lineSeparator());
        for (String line : lines) {
            int n = Integer.parseInt(line);
            if (n % 2 == 0) {
                System.out.println(n + " - число четное");
            } else {
                System.out.println(n + " - число не четное");
            }
        }
    }
}
