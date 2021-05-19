package ru.job4j.generics;

/**
 * Создаем 3 модели данных, которые образуют иерархию
 * наследования: Animal - Predator - Tiger.
 * Tiger - Модель данных описывающая тигра. (Наследуется от Predator).
 */
public class Tiger extends Predator {
    public Tiger(String name, int age) {
        super(name, age);
    }
}
