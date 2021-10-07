package ru.job4j.gc;

import java.util.Random;

/**
 * Виды сборщиков мусора.
 * Задание:
 * 1. Используя различные ключи запуска, запустить предложенные сборщики мусора.
 *
 * Класс предназначен для непрерывного заполнения памяти,
 * с целю мониторинга срабатывания GC.
 */
public class GCTypeDemo {
    public static void main(String[] args) {
        Random random = new Random();
        int length = 100;
        String[] data = new String[1_000_000];
        for (int i = 0; ; i = (i + 1) % data.length) {
            data[i] = String.valueOf(
                    (char) random.nextInt(255)
            ).repeat(length);
        }
    }
}
