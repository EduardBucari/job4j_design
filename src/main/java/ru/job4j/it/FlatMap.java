package ru.job4j.it;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Collections;

/**
 * FlatMap для Iterator^Iterator>
 * В Stream API есть метод flatMap. Он позволяет получить из элемента потока другой поток.
 *
 * В этом задании нужно получить из элемента потока другой поток.
 * Класс FlatMap принимает объект вложенных итераторов.
 * В классе нужно реализовать два метода: next и hasNext.
 * Метод next должен последовательно вернуть числа из вложенных итераторов.
 * В этом задании нельзя копировать элементы во временный список.
 * Результат проверить на готовых тестах.
 */
public class FlatMap<T> implements Iterator<T> {
    private final Iterator<Iterator<T>> data;
    private Iterator<T> cursor = Collections.emptyIterator();

    public FlatMap(Iterator<Iterator<T>> data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        while (cursor.equals(Collections.emptyIterator()) && cursor.hasNext()) {
            cursor.next();
        }
        if (!cursor.hasNext() && data.hasNext()) {
            cursor = data.next();
        }
        return cursor.hasNext();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return cursor.next();
    }

    public static void main(String[] args) {
        Iterator<Iterator<Integer>> data = List.of(
                List.of(1, 2, 3).iterator(),
                List.of(4, 5, 6).iterator(),
                List.of(7, 8, 9).iterator()
        ).iterator();
        FlatMap<Integer> flat = new FlatMap<>(data);
        while (flat.hasNext()) {
            System.out.println(flat.next());
        }
    }
}
