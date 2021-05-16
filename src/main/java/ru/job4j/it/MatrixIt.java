package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Итератор для двухмерного массива int[][].
 * Цель итератора переместить указатель на нужную ячейку.
 * Итератор не копирует элементы в новую коллекцию, а переводит указатель.
 * В этом примере указатель имеет два числа row и column.
 * Задание:
 * Реализуйте методы next и hasNext.
 * Копировать двухмерный в одномерный массив не нужно. Это не верное решение.
 * Нужно с помощью чисел row и column двигать указатель.
 * Добавлять новые поля в класс MatrixIt не нужно.
 * Проверить на готовых тестах.
 */
public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        for (; row < data.length; row++) {
            if (column < data[row].length) {
                return true;
            }
            column = 0;
        }
        return false;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[row][column++];
    }
}
