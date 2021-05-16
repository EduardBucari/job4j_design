package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

/**
 * Создать итератор возвращающий только четные цифры.
 * Итератор должен принимать список произвольных чисел.
 * методы:
 * it.next() - возвращают только четные числа. В этом примере - это 4 и 2,
 * и в случае отсутствия элементов к возврату генерирует NoSuchElementException.
 * it.hasNext() - возвращает true, только если в массиве есть четные перед указателем.
 *
 * В коде не должно быть пустых методов, если ваша программа
 * не поддерживает какой-либо функционал задекларированный
 * в интерфейсе - прокидывайте UnsupportedOperationException.
 */
public class EvenNumIt implements Iterator<Integer> {
    private int pos = 0;
    private final int[] arr;

    public EvenNumIt(final int... numbers) {
        this.arr = numbers;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void forEachRemaining(Consumer<? super Integer> action) {
        while (hasNext()) {
            action.accept(next());
        }
    }

    @Override
    public boolean hasNext() {
        while (pos < arr.length) {
            if (arr[pos] % 2 == 0) {
                return true;
            }
            pos++;
        }
        return false;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return arr[pos++];
    }
}
