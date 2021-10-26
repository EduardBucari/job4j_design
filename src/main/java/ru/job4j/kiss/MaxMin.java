package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

/**
 * Принципы проектирования приложений: Kiss, Dry и Yagni.
 * KISS - keep it simple and short. Код должен быть простым и коротким.
 * DRY - don't repeat your self. Дословно "не повторяй себя".
 *       Используй уже существующие методы, чтобы решить новую задачу. Не копируйте код.
 * YAGNI - You aren't going need it (решить задачу уже существующими методами).
 *
 * Задание:
 * 1. Разработайте класс для поиска максимального и минимального элемента по критерию java.util.Comparator.
 * 2. При решении этой задачи не использовать Stream API и методы Collections. Лямбды использовать можно.
 * 3. Написать тесты на каждый из методов.
 */
public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        return getResult(value, comparator, x -> x > 0);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return getResult(value, comparator, x -> x < 0);
    }

    private <T> T getResult(List<T> value, Comparator<T> comparator, Predicate<Integer> check) {
        T rsl = value.get(0);
        for (T val : value) {
            if (check.test(comparator.compare(val, rsl))) {
                rsl = val;
            }
        }
        return rsl;
    }
}
