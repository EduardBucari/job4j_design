package ru.job4j.collection.map;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

/**
 * Реализовать собственную структуру данных - HashMap
 * Требования:
 * 1. Разрешение коллизий реализовывать НЕ нужно.
 *    Метод вставки в случае отсутствия места должен возвращать false.
 * 2. Метод get() в случае отсутствия значения должен возвращать null, в противном случае само значение.
 * 3. Метод remove() в случае успешного удаления должен возвращать true, в противном случае false.
 * 4. Итератор должен обладать fail-fast поведением
 * 5. Хэш-таблица должна быть динамической, т.е. расширяться при необходимости.
 *
 * Задание:
 * 1. Реализовать хэш-функцию (метод  hash()) на ваш выбор
 * 2. Реализовать методы SimpleMap
 * 3. Написать как минимум по 2 теста на каждый публичный метод мапы.
 */
public class SimpleMap<K, V> implements Iterable<V> {
    private int modCount;
    private int capacity = 1 << 4; //16
    @SuppressWarnings("unchecked")
    private Node<K, V>[] table = new Node[capacity];
    private int size;
    private static final float LOAD_FACTOR = 0.75F;

    /**
     * Метод boolean insert(K key, V value) вставляет запись в таблицу по ключу и его значению.
     * @param key
     * @param value
     * @return true в случае удачной вставки, иначе false.
     */
    public boolean insert(K key, V value) {
        int hashCode = hash(key);
        if (table[hashCode] == null) {
            if (size >= capacity * LOAD_FACTOR) {
                expandTable();
            }
            table[hashCode] = new Node<>(key, value);
            size++;
            modCount++;
            return true;
        }
        return false;
    }

    /**
     * Метод V get(K key) возвращает значение заданного ключа.
     * @param key ключ;
     * @return значение;
     */
    public  V get(K key) {
        int hashCode = hash(key);
        if (table[hashCode] != null && table[hashCode].getKey().equals(key)) {
            return table[hashCode].getValue();
        }
        throw new NoSuchElementException();
    }

    /**
     * Метод delete(K key) удаляет значение по ключу.
     * @param key ключ;
     * @return true в случае удачного удаления, иначе false.
     */
    public boolean delete(K key) {
        int hashCode = hash(key);
        if (table[hashCode] != null && table[hashCode].getKey().equals(key)) {
            table[hashCode] = null;
            size--;
            modCount++;
            return true;
        }
        return false;
    }

    /**
     * Метод hash(K key) - метод расчёта и сжатия хэш-кода ключа.
     * @param key Ключ.
     * @return индекс бакета.
     */
    private int hash(K key) {
        int h;
        if (key == null) {
            return 0;
        } else {
            h = key.hashCode();
            return (capacity - 1) & (h ^ (h >>> 16));
        }
    }

    /**
     * Метод size() возвращает размер хранилища.
     * @return размер хранилища.
     */
    public int size() {
        return capacity;
    }

    /**
     * Метод getPrime(int min) возвращает первое простое число > min
     * @param min Минимальное число от которого выполняется поиск простого числа.
     * @return Первое просто число > min.
     */
    private int getPrime(int min) {
        for (int j = min + 1; true; j++) {
            if (isPrime(j)) {
                return j;
            }
        }
    }

    /**
     * Метод isPrime(int n) проверяет число n на простоту.
     * @param n Проверяемое число на простоту.
     * @return true в случае если число простое, иначе false.
     */
    private boolean isPrime(int n) {
        for (int j = 2; j * j <= n; j++) {
            if (n % j == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Рост хэш-таблицы при нехватке места для вставки нового элемента.
     * Метод выполняет перехеширование.
     */
    @SuppressWarnings("unchecked")
    private void expandTable() {
        capacity = getPrime(capacity * 2);
        Node<K, V>[] tempTable = new Node[capacity];
        for (Node<K, V> node : table) {
            if (node != null) {
                tempTable[hash(node.getKey())] = node;
            }
        }
        table = tempTable;
    }

    /**
     * Статический вложенный класс (Static nested class).
     * @param <K> Ключ.
     * @param <V> Значение ключа.
     */
    private static class Node<K, V> {
        K key;
        V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

    @Override
    public Iterator<V> iterator() {
        return new Iterator<>() {
            private int index = 0;
            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return index < size;
            }

            @Override
            public  V next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                while (table[index] == null) {
                    index++;
                }
                return table[index++].getValue();
            }
        };
    }
}
