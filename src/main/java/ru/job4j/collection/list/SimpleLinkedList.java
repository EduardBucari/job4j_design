package ru.job4j.collection.list;

import java.util.Iterator;
import java.util.Objects;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

/**
 * Создать контейнер на базе связанного списка.
 * Необходимо создать связный список с методами:
 * 1) add(E value); (добавляет в конец)
 * 2) E get(int index); (перебирает элементы до указанного индекса
 *    и возвращает значение из найденной ноды)
 * 3) реализовать интерфейс Iterable^E>.
 * Внутри контейнер должен базироваться на связанном списке(Node^E> node).
 * Контейнер должен быть динамическим, т.е. увеличиваться по мере добавления элементов.
 *
 * Итератор должен реализовывать fail-fast поведение,
 * т.е. если с момента создания итератора коллекция подверглась структурному изменению,
 * итератор должен кидать ConcurrentModificationException.
 * Это достигается через введение счетчика изменений - modCount.
 * Каждая операция, которая структурно модифицирует
 * коллекцию должна инкрементировать этот счетчик.
 *
 * В методах, где используется индекс нужно делать валидацию.
 * Индекс должен находиться в рамках добавленных элементов.
 *
 * Итератор должен работать без метода get(), потому что получение
 * по индексу долгая операция для связных списков.
 */
public class SimpleLinkedList<E> implements List<E> {
    private Node<E> head;
    private Node<E> tail;
    private int modCount;
    private int size;

    /**
     * Метод isEmpty() проверяет контейнер на пустоту.
     * @return true если контейнер пуст, иначе false.
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Метод add(E value) добавляет элемент данных в конец списка.
     * @param value - Добавляемый элемент в контейнер.
     */
    public void add(E value) {
        Node<E> node = new Node<>(value, null);
        if (isEmpty()) {
            head = node;
        } else {
            tail.next = node;
        }
        tail = node;
        modCount++;
        size++;
    }

    /**
     * Метод E get(int index) возвращает элемент данных по заданному индексу.
     * @param index - Индекс возвращаемого элемента.
     * @return -  Возвращаемый элемент.
     */
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> current = head;
        for (int i = 0; i < size; i++) {
            if (i == index) {
                break;
            }
            current = current.next;
        }
        return current.value;
    }

    private static class Node<E> {
        E value;
        Node<E> next;

        public Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
        }
    }

    /**
     * Реализуем интерфейс Iterable^E>.
     * @return - Возвращаемый итератор
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private final int expectedModCount = modCount;
            private Node<E> pointer = head;

            @Override
            public boolean hasNext() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                return pointer != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E value = pointer.value;
                pointer = pointer.next;
                return value;
            }
        };
    }
}