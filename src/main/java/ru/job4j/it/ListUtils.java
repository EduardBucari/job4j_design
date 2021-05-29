package ru.job4j.it;

import java.util.*;
import java.util.function.Predicate;

/**
 * ListIterator.
 * Ранее мы реализовывали и использовали Iterator, который обладает поведением fail-fast.
 * Что это значит? Это значит, что если мы по ходу итерирования меняем коллекцию,
 * то получаем исключение ConcurrentModificationException.
 * В качестве альтернативы "обычному" итератору, есть ListIterator.
 * Он обладает fail-safe поведением, это значит, что мы можем менять коллекцию
 * по ходу итерирования, но только с помощью самого итератора.
 * Таким образом, чтобы как-то манипулировать списком во время
 * итерирования, нужно использовать методы ListIterator, такие как:
 * addAfter(), addBefore(), removeIf(), replaceIf() и removeAll().
 * Задание:
 * 1. Реализовать вышеперечисленные методы ListIterator.
 * 2. Допишите тесты на каждый из методов.
 */
public class ListUtils {

    /**
     * Метод addBefore() вставляет список элементов до заданного индекса;
     * @param list - список элементов,
     * @param index - индекс вставки,
     * @param value - значение вставляемого элемента,
     * @param <T> - тип элементов в списке.
     */
    public static <T> void addBefore(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> i = list.listIterator();
        while (i.hasNext()) {
            if (i.nextIndex() == index) {
                i.add(value);
                break;
            }
            i.next();
        }
    }

    /**
     * Метод addAfter() вставляет список элементов после заданного индекса;
     * @param list - список элементов,
     * @param index - индекс вставки,
     * @param value - значение вставляемого элемента,
     * @param <T> - тип элементов в списке.
     */
    public static <T> void addAfter(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> i = list.listIterator();
        while (i.hasNext()) {
            if (i.previousIndex() == index) {
                i.add(value);
                break;
            }
            i.next();
        }
    }

    /**
     * Метод removeIf() удаляет все элементы, которые удовлетворяют предикату.
     * @param list - список элементов.
     * @param filter - Функциональный интерфейс Predicate,
     * @param <T> - тип элементов в списке.
     * @return - список элементов не удовлетворяющий предикату.
     */
    public static <T> List<T> removeIf(List<T> list, Predicate<T> filter) {
        ListIterator<T> i = list.listIterator();
        while (i.hasNext()) {
            if (filter.test(i.next())) {
                i.remove();
            }
        }
        return list;
    }

    /**
     * Метод replaceIf() заменяет все элементы, которые удовлетворяют предикату;
     * @param list - список элементов.
     * @param filter - Функциональный интерфейс Predicate,
     * @param value - значение вставляемого элемента,
     * @param <T> - тип элементов в списке.
     * @return - список элементов не удовлетворяющий предикату.
     */
    public static <T> List<T> replaceIf(List<T> list, Predicate<T> filter, T value) {
        ListIterator<T> i = list.listIterator();
        while (i.hasNext()) {
            if (filter.test(i.next())) {
                i.set(value);
            }
        }
        return list;
    }

    /**
     * Метод removeAll() удаляет из списка те элементы, которые есть в elements.
     * @param list - список элементов.
     * @param elements - список удаленных элементов.
     * @param <T> - тип элементов в списке.
     * @return - получившийся после удаления, список элементов.
     */
    public static <T> List<T> removeAll(List<T> list, List<T> elements) {
        ListIterator<T> i = list.listIterator();
        while (i.hasNext()) {
            if (elements.contains(i.next())) {
                i.remove();
            }
        }
        return list;
    }
}
