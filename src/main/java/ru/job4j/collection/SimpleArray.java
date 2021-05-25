package ru.job4j.collection;

import java.util.Iterator;
import java.util.Objects;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

/**
 * Динамический список на массиве.
 * - В этом задании мы создадим реализацию ArrayList.
 * - Внутри контейнер должен базироваться на массиве Object[] container.
 * - Контейнер должен быть динамическим, т.е. при полном заполнении увеличиваться.
 * - Итератор должен реализовывать fail-fast поведение,
 *   т.е. если с момента создания итератора в коллекцию добавили новый элемент,
 *   итератор должен кидать ConcurrentModificationException.
 *   Это достигается через введение счетчика изменений - modCount.
 * - Счетчик изменений - modCount. Каждая операция, которая структурно модифицирует коллекцию
 *   должна увеличивать этот счетчик. В свою очередь итератор запоминает значение
 *   этого счетчика на момент своего создания (expectedModCount),
 *   а затем на каждой итерации сравнивает сохраненное значение,
 *   с текущим значением поля modCount, если они отличаются, то генерируется исключение.
 * - Добавьте самостоятельно тесты проверяющее расширение массива.
 * @param <T> - обобщенный тип данных.
 */
public class SimpleArray<T> implements Iterable<T> {
    private Object[] container; // Создаем массив для внутреннего контейнера.
    private static final int DEFAULT_CAPACITY = 15;
    private int size;
    private int modCount; // счетчик изменений.

    /**
     * Данный конструктор создает объект SimpleArray заданной вместимости.
     * @param initCapacity - инициализация вместимости контейнера.
     * @throws IllegalArgumentException - выбрасывается исключение,
     *         если вместимость отрицательная или равна 0.
     */
    public SimpleArray(int initCapacity) {
        if (initCapacity <= 0) {
            throw new IllegalArgumentException();
        }
        container = new Object[initCapacity];
    }

    /**
     * Данный дефолтный конструктор создает
     * объект SimpleArray вместимостью 15 элементов.
     */
    public SimpleArray() {
        this(DEFAULT_CAPACITY);
    }

    // Приступаем к реализации основных методов SimpleArray:
    /**
     *  Метод add(T model) - добавляет элемент в конец массива SimpleArray;
     * @param model - Добавляемый элемент.
     */
    public void add(T model) {
        resize();
        container[size] = model;
        size++;
        modCount++; //реализация fail-fast поведения за счет изменений счетчика - modCount.
    }

    /**
     * Метод add(int index, T model) добавляет элемент
     * в контейнер согласно заданному индексу.
     * @param index - индекс в контейнере,
     * @param model - добавляемый элемент.
     */
    public void add(int index, T model) {
        Objects.checkIndex(index, size + 1);
        resize();  // Для проверки индекса используйте метод Objects.checkIndex()
        System.arraycopy(container, index, container, index + 1,
                size - index);
        container[index] = model;
        modCount++; //реализация fail-fast поведения за счет изменений счетчика - modCount.
        size++;
    }

    /**
     * Метод get(int index) возвращает элемент по заданному индексу.
     * Необходимо обработать исключение выхода за
     * доступный диапозон IndexOutOfBoundException.
     * @param index - индекс возвращаемого элемента.
     * @return - возвращаемый элемент.
     */
    @SuppressWarnings("unchecked")
    public T get(int index) {
        Objects.checkIndex(index, size);
        return (T) container[index];
    }
     // Метод set(int index, T model) устанавливает элемент по заданному индексу.
    public void set(int index, T model) {
        Objects.checkIndex(index, size);
        container[index] = model;
        modCount++; //реализация fail-fast поведения за счет изменений счетчика - modCount.
    }

    /**
     * Метод clear() - очищает контейнер.
     */
    public void clear() {
        size = 0;
        container = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Метод size() возвращает рзмер контейнера.
     *
     * @return - размер контейнера
     */
    public int size() {
        return size;
    }

    /**
     * Метод ifEmpty() проверяет контейнер на пустоту.
     *
     * @return true если контейнер пуст, иначе false.
     */
    public boolean ifEmpty() {
        return size == 0;
    }

    /**
     * Метод getFirst возврщает первый элемент в контейнере.
     *
     * @return - первый элемент в контейнере.
     * @throws - java.util.NoSuchElementException если контейнер пустой.
     */
    public T getFirst() {
        if (ifEmpty()) {
            throw new NoSuchElementException();
        }
        return get(0);
    }

    /**
     * Метод getLast возврщает последний элемент в контейнере.
     *
     * @return - последний элемент в контейнере.
     * @throws - java.util.NoSuchElementException если контейнер пустой.
     */
    public T getLast() {
        if (ifEmpty()) {
            throw new NoSuchElementException();
        }
        return get(size - 1);
    }

    /**
     * Метод resize() меняет длину контейнера и реализует динамику.
     */
    public void resize() {
        if (container.length == size) {
            Object[] newArray = new Object[container.length * 2];
            System.arraycopy(container, 0, newArray, 0, size);
            container = newArray;
        }
    }

    /**
     * Метод remove(int index) - удаляет элементы из контейнера,
     * согласно заданному индексу.
     * Необходимо обработать исключение IndexOutOfBoundException - выход
     * индекса за доступный диапазон.
     * @param index - индекс удаленного элемента.
     * @return - удаленный элемент.
     */
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        Objects.checkIndex(index, size);
        T removedElement = (T) container[index];
        System.arraycopy(container, index + 1, container, index,
                size - index - 1);
        size--;
        modCount++; //реализация fail-fast поведения за счет изменений счетчика - modCount.
        return removedElement;
    }

    /**
     * Метод contains(T element) проверяет наличие
     * специфического элемента в контейнере.
     * @param element - специфический элемент в контейнере.
     * @return true если элемент сожержится в контейнере.
     * иначе метод возвращает false.
     */
    public boolean contains(T element) {
        for (int i = 0; i < size; i++) {
            if (container[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            int iter;
            final int expectedModCount = modCount; // fail-fast поведения

            @Override
            public boolean hasNext() {
                return iter < size;
            }

            @Override
            @SuppressWarnings("unchcked")
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return (T) container[iter++];
            }
        };
    }
}