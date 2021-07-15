package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

/**
 * Именованные аргументы.
 * Консольные программы требуют передачи разных параметров.
 * Мы работаем с виртуальной машиной Java (jvm). Ее запуск может происходить с ключами.
 * Например, можно указать количество памяти под программу и кодировку:
 * java -Xmx=512 -encoding=UTF-8
 * Эти параметры можно указывать в произвольном порядке. Можно некоторые заполнять, а некоторые пропускать.
 * Задание:
 * 1. Реализовать класс ArgsName и написать программу,
 *    которая принимает массив параметров и разбивает их на пары: ключ, значение.
 * 2. Результат проверить на готовом тесте.
 */
public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    /**
     * Метод get возвращает значение ключа.
     *
     * @param key Ключ.
     * @return Значение
     */
    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("No such parameter exists");
        }
        return values.get(key);
    }

    /**
     * Приватный метод parse(),
     * парсит массив параметров полученный в виде аргумента.
     *
     * @param args Массив параметров в формате -ключ=значение.
     */
    private void parse(String[] args) {
        /* TODO parse args to values. */
        if (args.length == 0) {
            throw new IllegalArgumentException();
        }
        for (String arg : args) {
            String[] buffArg = arg.split("=");
            if (buffArg.length < 2) {
                throw new IllegalArgumentException("argument must be: key = value");
            }
            values.put(buffArg[0].substring(1), buffArg[1]);
        }
    }

    /**
     * ArgsName of() - Фабричный метод.
     *
     * @param args Массив параметров в формате -ключ=значение.
     * @return Объект типа ArgsName с распарсенными параметрами.
     */
    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }
}
