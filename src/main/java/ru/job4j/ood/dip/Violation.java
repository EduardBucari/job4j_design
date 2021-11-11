package ru.job4j.ood.dip;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Принцип инверсии зависимостей.
 * Принцип DIP гласит:
 * Модули верхнего уровня не должны зависеть от модулей нижнего уровня.
 * И те и другие должны зависеть от абстракций.
 * Абстракции не должны зависеть от деталей. Детали должны зависеть от абстракций.
 * Задание:
 * Придумайте 3 примера, когда происходит нарушение принципа DIP.
 *
 * Пример 1:
 * Нарушение DIP - в поле класса Violation происходит прямая зависимость от конкретной реализации.
 */
public class Violation {
    private HashSet<Integer> hashSet = new HashSet<>();
}

/**
 * Пример 2:
 * Нарушение DIP - возвращаемое значение метода имеет конкретную реализацию.
 */
class Violation2 {
    public ArrayList<String> list() {
        /*
        some code
         */
        return new ArrayList<>();
    }
}

/**
 * Пример 3:
 * Нарушение DIP - аргумент конструктора имеет конкретную реализацию.
 */
class Violation3 {
    private Map<Integer, Integer> map;

    public Violation3(HashMap<Integer, Integer> map) {
        this.map = map;
    }
}
