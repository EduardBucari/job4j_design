package ru.job4j.collection.map;

import java.util.*;

public class HashMapSimple<K, V> implements Iterable<HashMapSimple.Node<K, V>> {
    /**
     * Начальный массив должен быть степенью двойки
     */
    private Node<K, V>[] hashTable = new Node[16]; //структура Node ниже
    /**
     * Коэффициент загрузки используется, когда ничего не указано в конструкторе.
     */
    private static final float LOAD_FACTOR = 0.75f;
    /**
     * Порог, при котором нужно увеличивать размер массива
     */
    private float threshold = hashTable.length * LOAD_FACTOR;
    /**
     * Количество отображений ключ-значение, содержащихся в этой карте.
     * количество заполненных ячеек массива
     */
    private transient int size = 0;
    /**
     * Количество раз, когда этот HashMap был структурно изменен
     * Структурными модификациями являются те, которые изменяют
     * количество отображений в HashMap или иным образом изменить
     * его внутреннюю структуру (например, Перехеширование).
     * Это поле используется для создания итераторов в коллекциях
     * HashMap отказоустойчивый. (См. ConcurrentModificationException).
     * --
     * счетчик изменений
     */
    private transient int modCount = 0;

    /**
     * Вставка объекта
     * В данном случае, если возникает коллизия, просто возвращаем false
     */
    public boolean insert(K key, V value) {
        boolean result = false;
        if (size >= threshold) {
            grow();
        }
        int index = indexFor(hashKey(key));
        if (hashTable[index] == null) {
            hashTable[index] = new Node<>(key, value);
            result = true;
            size++;
            modCount++;
        }
        return result;
    }

    /**
     *
     * Примечание: В методе get(), NPE падает,
     * потому что вы вызываете key, у null.
     * Нужно предварительно делать проверку на null
     *
     */
    public V get(K key) {
        int index;
        V result = null;
        if (key != null) {
            index = indexFor(hashKey(key));
            if (hashTable[index] != null
                    && Objects.equals(key, hashTable[index].key)) {
                result = hashTable[index].value;
            }
        }
        return result;
    }

    public boolean delete(K key) {
        int index;
        boolean result = false;
        if (key != null) {
            index = indexFor(hashKey(key));
            if (hashTable[index] != null
                    && Objects.equals(key, hashTable[index].key)) {
                hashTable[index] = null;
                size--;
                modCount++;
                result = true;
            }
        }
        return result;
    }

    /**
     * Расширение массива по необходимости в 2 раза
     * При этом нужно учесть, что потребуется
     * новое перераспределение всех элементов
     * по массиву новой длины
     */
    private void grow() {
        Node<K, V>[] oldHashTable = hashTable;
        int oldSize = oldHashTable.length;
        hashTable = new Node[oldSize * 2];
        threshold = hashTable.length * LOAD_FACTOR;

        for (int i = 0; i < oldSize; i++) {
            if (oldHashTable[i] != null) {
                K key = oldHashTable[i].getKey();
                int indexNew = indexFor(hashKey(key));
                hashTable[indexNew] = oldHashTable[i];
            }
        }
    }

    /**
     * Расчет индекса (позиции) в массиве
     */
    private int indexFor(int hash) {
        return hash & (hashTable.length - 1);
    }

    /**
     * Метод для работы с ключом, который позволяет сделать довольно редкий hash
     * Здесь hashcode берется почему-то java.lang.object
     */
    private int hashKey(K key) {
        int h;
        if (key == null) {
            h = 0;
        } else {
            h = key.hashCode();
            h = h ^ (h >>> 16);
        }
        return h;
    }

// Структура узла

    public static class Node<K, V> {
        private final K key;
        private V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        @Override
        public String toString() {
            return key + " = " + value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Node)) {
                return false;
            }
            Node<?, ?> node = (Node<?, ?>) o;
            return Objects.equals(key, node.key)
                    && Objects.equals(value, node.value);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }
    }

// Итератор

    @Override
    public Iterator<Node<K, V>> iterator() {
        return new Iterator<>() {
            private int cursor = 0;
            private int expectedModCount = modCount;

            private void checkForComodification() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
            }

            @Override
            public boolean hasNext() {
                checkForComodification();
                while (hashTable[cursor] == null
                        && cursor < hashTable.length - 1) {
                    cursor++;
                }
                return hashTable[cursor] != null;
            }

            @Override
            public Node<K, V> next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return hashTable[cursor++];
            }
        };
    }
}