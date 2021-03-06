package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Удалить head в односвязном списке.
 * В этом задании необходимо реализовать метод delete для односвязного списка:
 * deleteFirst() и deleteLast() путём создания цепочки ссылок
 * и таким образом организовать контейнер для данных.
 *
 * Head - всегда указывает на первый элемент.
 * Процесс удаления в такой структуре сводится к обнулению ссылки
 * на следующий узел (node.next = null;)
 *
 * В методе delete должна быть проверка, что head != null. Этот случай проверяется в тесте.
 */

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;

    /**
     * Метод add(T value) добавляет элемент данных в конец списка.
     * @param value - добавляемый элемент в контейнер.
     */
    public void add(T value) {
        Node<T> node = new Node<>(value, null);
        if (isEmpty()) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    /**
     * Метод deleteFirst() удаляет первый элемент в цепочке данных,
     * так же это называется "обнуление ссылки на следующий узел".
     * @return - удаленный элемент.
     */
    public T deleteFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        T result = head.value;
        head = head.next;
        return result;
    }

    /**
     * Метод isEmpty() проверяет односвязный список на пустоту.
     * @return true если список пустой, иначе false.
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Метод deleteLast() удаляет элемент данных в конце списка.
     * @return - значение удаленного элемента.
     */
    public T deleteLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node<T> current = head;
        Node<T> previous = head;

        while (current.next != null) {
            previous = current;
            current = current.next;
        }
        if (current == previous) {
            head = null;
        }
        previous.next = null;
        return current.value;
    }

    /**
     * Задание: Перевернуть связанный список.
     * Реализовать Метод revert(), он должен переставить элементы в обратном порядке:
     * 1. Сначала мы делаем валидацию. Пустой список или список из одного элемента
     *    не имеет смысла переворачивать, поэтому возвращаем false.
     * 2. Далее уже начинаем менять ссылки.
     *    Суть алгоритма заключается в установлении текущему ссылки на следующий в качестве предыдущего.
     *    Далее мы просто обновляем значения текущее и предыдущее.
     *    Наконец, предыдущее значение ставится головой списка.
     */
    public void revert() {
        if (isEmpty()) {
            return;
        }
        Node<T> current = head;
        Node<T> previous = head;

        while (current != null) {
            Node<T> next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        head = previous;
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }

    /**
     * Реализуем интерфейс Iterable c целью прикручивания
     * структуры данных к циклу типа forEach,
     * Возвращаем итератор бегущий по элементам данных типа Т.
     * @return итератор.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }
}