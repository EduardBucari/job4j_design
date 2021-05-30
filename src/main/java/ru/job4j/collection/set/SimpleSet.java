package ru.job4j.collection.set;

import ru.job4j.collection.SimpleArray;

import java.util.Iterator;
import java.util.Objects;

/**
 * Реализовать коллекцию Set на массиве.
 * Задание:
 * 1. Реализовать коллекцию SimpleSet,
 *    а точнее ее методы: add(T value), contains(T value) и iterator().
 *    Коллекция не должна хранить дубликаты.
 *    Для хранения использовать SimpleArray из прошлого задания.
 * 2. Для сравнения объектов использовать метод: Objects.equals
 * 3. Дописать тесты на наличие дубликатов.
 *
 */
public class SimpleSet<T> implements Set<T> {
    private final SimpleArray<T> set = new SimpleArray<>();

    /**
     * Метод add(T value) добавляет элемент в коллекцию включая null,
     * при этом исключая дубликаты.
     * @param value Добавленное значение.
     * @return true в случае удачного добавления, иначе false.
     */
    @Override
    public boolean add(T value) {
        if (!contains(value)) {
            set.add(value);
            return true;
        }
        return false;
    }

    /**
     * Метод contains(T value) проводит проверку или Валидацию на дубликаты,
     * вынесен в отдельный метод по причине fail-fast итератора.
     * @param value значение проверяемое на наличие дубликата в контейнере.
     * @return true в случае наличия дубликата в контейнере, иначе false.
     */
    @Override
    public boolean contains(T value) {
        for (T element : set) {
            if (Objects.equals(value, element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}
