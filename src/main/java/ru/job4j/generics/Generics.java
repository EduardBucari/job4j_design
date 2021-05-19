package ru.job4j.generics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 *  Что такое обобщенные типы (generics).
 *  Задание:
 *  1. Добавьте 3 модели данных, которые образуют иерархию
 *     наследования: Animal - Predator - Tiger.
 *  2. Данный код содержит ошибки компиляции, вам необходимо их поправить,
 *     закомментировав строки которые их вызывают.
 *  3. Написать код использующий 3 модели:
 *     WildCard, Bounded WildCard, Lower bounded WildCard.
 */
public class Generics {
    public static void main(String[] args) {
        Generics gen = new Generics();
        List<Animal> first = new ArrayList<>();
        List<Predator> second = new ArrayList<>();
        List<Tiger> third = new ArrayList<>();
        first.add(new Animal("Animal", 7));
        second.add(new Predator("Predator", 6));
        third.add(new Tiger("Tiger", 5));

        gen.printObject(first);
        gen.printObject(second);
        gen.printObject(third);
        System.out.println();

        gen.printBoundedWildCard(second);
        gen.printBoundedWildCard(third);
        System.out.println();

        gen.printLowerBoundedWildCard(first);
        gen.printLowerBoundedWildCard(second);
    }

    /**
     * Модель WildCard
     * Метод printObject - работает без ограничений, т.е. в него можно
     * передавать коллекцию, которая хранит любые типы.
     * @param list - Список данных.
     */
    public void printObject(List<?> list) {
        for (Iterator<?> it = list.iterator(); it.hasNext();) {
            Object next = it.next();
            System.out.println("Текущий элемент: " + next);
        }
    }

    /**
     * Модель Bounded WildCard.
     * Метод printBoundedWildCard - должен иметь ограничение
     * сверху и ограничиваться классом Predator.
     * @param list - Список данных.
     */
    public void printBoundedWildCard(List<? extends Predator> list) {
        for (Iterator<? extends Predator> it = list.iterator(); it.hasNext();) {
            Object next = it.next();
            System.out.println("Текущий элемент: " + next);
        }
    }

    /**
     * Модель Lower bounded WildCard.
     * Метод printLowerBoundedWildCard - должен иметь
     * ограничение снизу и ограничиваться классом Predator.
     * @param list - Список данных.
     */
    public void printLowerBoundedWildCard(List<? super Predator> list) {
        for (Iterator<? super Predator> it = list.iterator(); it.hasNext();) {
            Object next = it.next();
            System.out.println("Текущиий элемент: " + next);
        }
    }
}
