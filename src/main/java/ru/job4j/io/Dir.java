package ru.job4j.io;

import java.io.File;

/**
 * File - Применение класса File. Взаимодействие с файловой системой.
 * Главным элементом файловой системы является объект java.io.File.
 * File может быть и текстовым документом и директорией.
 * Рассмотрим задачу с получением всех элементов директории.
 * Наш проект лежит в папке c:\projects\.
 * Напишем программу, которая проверяет, что директория projects - это директория и выведем ее содержимое.
 *
 * Разберем методы:
 * 1. Проверяем, что файл существует -            if (!file.exists()) {
 * 2. Проверяет, что файл - это директория -      if (!file.isDirectory()) {
 * 3. Получаем список файлов в этой директории -  for (File subfile : file.listFiles()) {
 *
 * Задание.
 * Доработать программу ru.job4j.io.Dir,
 * так что бы она выводила только имя файла и его размер.
 * Размер файла измеряется методом length()
 */
public class Dir {
    /**
     * Метод вывода файлов по заданному пути и их размера.
     * @param args - Аргументы метода.
     */
    public static void main(String[] args) {
        final File file = new File("c:\\projects");
        if (!file.exists()) {
            throw new IllegalArgumentException(
                    String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(
                    String.format("Not directory %s", file.getAbsoluteFile()));
        }
        for (File subFile : file.listFiles()) {
            System.out.println(String.format("file: %s - size: %s",
                    subFile.getName(),
                    subFile.length()));
        }
    }
}