package ru.job4j.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

/**
 * Реализация кеша на SoftReference.
 * Задание 1:
 * Создать структуру данных типа кеш. Кеш должен быть абстрактный.
 * То есть необходимо, что бы можно было задать ключ получения объекта кеша
 * и в случае если его нет в памяти, задать поведение загрузки этого объекта в кеш.
 * (Кеш — это область локальной памяти, в которой хранится копия часто используемых данных.
 * Кэш — это временные файлы, сохраненные в памяти компьютера,
 * для быстрой загрузки по запросу пользователя.)
 *
 */
public abstract class AbstractCache<K, V> {
    protected final Map<K, SoftReference<V>> cache = new HashMap<>();

    /**
     * Метод отправляет данные в кэш.
     * @param key   Ключ данных в кэше.
     * @param value Данные отправляемые в кэш.
     */
    public void put(K key, V value) {
        cache.put(key, new SoftReference<>(value));
    }

    /**
     * Метод получения данных из кэша.
     * Если в кэше нет данных, подгружаются.
     * @param key Ключ получения данных из кэша.
     * @return Данные или null при отсутствии файла.
     */
    public V get(K key) {
        V result = cache.getOrDefault(key, new SoftReference<>(null)).get();
        if (result == null) {
            result = load(key);
            put(key, result);
        }
        return result;
    }

    /**
     * Абстрактный метод загрузки данных по ключу.
     * @param key Ключ данных типа K.
     * @return Данные типа V.
     */
    protected abstract V load(K key);
}