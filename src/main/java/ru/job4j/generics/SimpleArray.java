package ru.job4j.generics;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static java.util.Objects.checkIndex;

/**
 * Реализовать SimpleArray^T>:
 * В этом задании необходимо сделать универсальную обертку над массивом.
 * Добавить и реализовать 4 метода:
 * add(T model) - добавляет указанный элемент (model) в первую свободную ячейку;
 * set(int index, T model) - заменяет указанным элементом (model) элемент,
 *                           находящийся по индексу index;
 * remove(int index) - удаляет элемент по указанному индексу, все находящиеся справа
 *                     элементы при этом необходимо сдвинуть на единицу
 *                     влево (в середине массива не должно быть пустых ячеек);
 * get(int index) - возвращает элемент, расположенный по указанному индексу;
 *
 * Также, реализуйте интерфейс Iterable^T> - метод iterator() возвращает итератор,
 * предназначенный для обхода данной структуры.
 * Объект должен принимать количество ячеек. Структура не должна быть динамической.
 */
public class SimpleArray<T> implements Iterable<T> {
    private final Object[] elements;
    private int pos = 0;

    public SimpleArray(int size) {
        this.elements = new Object[size];
    }

    /**
     * Метод add(T model) - добавляет указанный элемент (model) в первую свободную ячейку;
     * @param model - Добавляемый элемент.
     * @throws IndexOutOfBoundsException - выход за пределы массива.
     */
    public void add(T model) throws IndexOutOfBoundsException {
        elements[pos++] = model;
    }

    /**
     * Метод set(int index, T model) - заменяет указанным элементом (model) элемент,
     *                                  находящийся по индексу - index;
     * Для проверки индекса используется метод Objects.checkIndex().
     *
     * @param index - индекс заменяемого элемента.
     * @param model - Новый добавляемый элемент.
     * @throws IndexOutOfBoundsException - выход за пределы массива.
     */
    public void set(int index, T model) throws IndexOutOfBoundsException {
        checkIndex(index, pos);
        elements[index] = model;
    }

    /**
     * Метод get(int index) - возвращает элемент, расположенный по указанному индексу;
     *
     * Для проверки индекса используется метод Objects.checkIndex().
     * Этот метод используется для проверки того, находится ли индекс в пределах заданной длины.
     * Он возвращает индекс, если 0 <= index < length.
     * В противном случае генерируется исключение IndexOutOfBoundsException.
     *
     * @param index - Индекс возвращаемого элемента.
     * @return - Возвращаемый элемент.
     * @throws IndexOutOfBoundsException - выход за пределы массива.
     */
    @SuppressWarnings("unchecked")
    public T get(int index) throws  IndexOutOfBoundsException {
        checkIndex(index, pos);
        return (T) elements[index];
    }

    /**
     * Метод remove(int index) - удаляет элемент по указанному индексу, все находящиеся справа
     *                           элементы при этом необходимо сдвинуть на единицу
     *                           влево (в середине массива не должно быть пустых ячеек);
     * @param index - Индекс удаленного элемента.
     * @throws IndexOutOfBoundsException - выход индекса за пределы массива.
     */
    public void remove(int index) throws IndexOutOfBoundsException {
        checkIndex(index, pos);
        System.arraycopy(elements, index + 1, elements, index,
                elements.length - index - 1);
        elements[--this.pos] = null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Iterator<T> iterator() {
        return new Iterator<>() {
            int counter = 0;

            @Override
            public boolean hasNext() {
                return counter < pos;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) elements[counter++];
            }
        };
    }
}
