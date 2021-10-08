package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * Читаем файл конфигурации.
 * Задание:
 * 1. Реализовать класс Config и метод load()
 *    Метод load должен загружать пару ключ-значение в Map values.
 * 2. Реализуйте метод value(String key) он должен возвращать значение ключа.
 * 3. Напишите тест ConfigTest.
 * 4. Дополните тест проверками ниже. Под каждый тест будет свой файл.
 * 1) на чтение файла с комментариями и пустыми строками
 * 2) на файла с нарушением шаблона ключ=значение (напр. ключ=)
 *    в этом случае нужно ожидать исключение IllegalArgumentException
 *
 * В тестах не прописан путь. Чтобы мы обратились к нашему файлу нужно использовать относительный путь.
 * Чтобы начать путь от корня проекта нужно вначале написать точку
 * String path = "./data/pair_without_comment.properties";
 * Обратите внимание, что папку data и сам файл нужно создать самому
 * и каждый метод теста будет работать со своим вариантом файла.
 * В этих тестах не должно быть полей класса.
 */
public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    /**
     * Метод load() - должен считать все ключи в карту values.
     * Важно в файле могут быть пустые строки и комментарии, их нужно пропускать.
     */
    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines()
                    .filter(e -> !e.contains("#") && e.contains("="))
                    .map(e -> e.split("="))
                    .filter(e -> e.length > 1)
                    .forEach(e -> values.put(e[0], e[1]));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод возвращает значение ключа.
     *
     * @param key Ключ.
     * @return Значение ключа.
     */
    public String value(String key) {
        return values.get(key);
    }
}