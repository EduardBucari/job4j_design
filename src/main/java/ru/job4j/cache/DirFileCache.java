package ru.job4j.cache;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Реализация кеша на SoftReference.
 * Задание 2:
 * Создать программу, эмулирующее поведение данного кеша.
 * Программа должна считывать текстовые файлы из системы и выдавать текст при запросе имени файла.
 * По умолчанию в кеше нет ни одного файла.
 * Текстовые файл должны лежать в одной директории.
 * Пример: Names.txt, Address.txt - файлы в системе.
 * При запросе по ключу Names.txt - кеш должен вернуть содержимое файла Names.txt.
 *
 * Важно! key это относительный путь к файлу в директории.
 */
public class DirFileCache extends AbstractCache<String, String> {
    private final String cachingDir;

    /**
     * Конструктор принимает директорию с файлами для кэширования.
     * @param cachingDir Директория расположения кэшируемых файлов.
     */
    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    /**
     * Метод загружает содержимое файла.
     * @param key Имя файла.
     * @return Содержимое файла.
     */
    @Override
    protected String load(String key) {
        String result = null;
        try {
            result = Files.readString(Paths.get(cachingDir + key));
        } catch (IOException e) {
            System.out.printf("Ошибка загрузки файла: %s%n", key);
        }
        return result;
    }
}
