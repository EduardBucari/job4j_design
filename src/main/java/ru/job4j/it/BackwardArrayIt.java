package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Шаблон "итератор".
 * Задание.
 * Реализуйте итератор для массива. Итератор должен отдавать элемнты в обратном порядке.
 * Шаблон "итератор" позволяет последовательно получить элементы набора данных.
 * Метод hasNext проверяет, если ли следующий элемент.
 * Метод next сдвигает указатель итератора. Указатель - это ссылка на элемент, который нужно вернуть.
 * Если в итераторе нет элементов и мы вызовем метод next,
 * в этом случае итератор должен сгенерировать исключение NoSuchElementException.
 * Проверить на готовых тестах.
 */
public class BackwardArrayIt implements Iterator<Integer> {
    private final int[] data;
    private int point = 0;

    public BackwardArrayIt(int[] data) {
        this.data = data;
        this.point = data.length - 1;
    }

    @Override
    public boolean hasNext() {
        return point >= 0;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[point--];
    }
}
