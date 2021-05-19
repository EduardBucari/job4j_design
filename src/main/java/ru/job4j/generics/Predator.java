package ru.job4j.generics;

/**
 * Создаем 3 модели данных, которые образуют иерархию
 * наследования: Animal - Predator - Tiger.
 * Predator - Модель данных описывающая хищника. (Наследуется от Animal).
 */
public class Predator  extends Animal {
    public Predator(String name, int age) {
        super(name, age);
    }
}
