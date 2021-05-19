package ru.job4j.generics;

/**
 * Создаем 3 модели данных, которые образуют иерархию
 * наследования: Animal - Predator - Tiger.
 * Animal - Модель данных описывающая животного.
 */
public class Animal {
    String name;
    int age;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Animal{"
                + "name='" + name + '\''
                + ", age=" + age
                + '}';
    }
}
