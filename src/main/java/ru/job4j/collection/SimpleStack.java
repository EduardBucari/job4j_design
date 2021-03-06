package ru.job4j.collection;

/**
 * Используя контейнер на базе связанного списка создать контейнер Stack.
 * Stack - примитивная структура данных.
 * Stack лучше всего реализовать на базе связанного списка, т.к.
 * Связанный список умеет быстро вставлять данные и удалять.
 * Это как раз те действия, которые нужны для Stack.
 *
 * Задание:
 * 1.Реализуйте класс Stack.
 * 2.Дописать функционал ForwardLinked, т.е. реализовать методы poll(), push().
 * Метод pop() - должен возвращать значение и удалять его из коллекции.
 * Метод push(T value) - помещает значение в коллекцию.
 *
 * При реализации стека на основе списка используются операции
 * добавления и удаления с одного и того же конца.
 * Например, можно сделать добавление в начало и удаление с начала.
 * Мы можем добавить метод удаления с конца deleteLast() либо добавления в начало addFirst().
 * Либо с другого конца, т.е. с хвоста списка. В итоге мы получим тоже самое поведение.
 * Ваша задача выбрать какой из методов реализовать и в дальнейшем использовать в стеке.
 */
public class SimpleStack<T> {
     private final ForwardLinked<T> forwardLinked = new ForwardLinked<T>();

    /**
     * Метод удаляет элемент из стека и возвращает его значение.
     * @return - возвращаемое значение.
     */
     public T pop() {
         return forwardLinked.deleteFirst();
     }

    /**
     * Метод push(T value) - помещает значение в коллекцию или в стек.
     * @param value - значение помещенное в стек.
     */
    public void push(T value) {
        forwardLinked.add(value);
    }

    /**
     * Метод isEmpty() проверяет стек на пустоту.
     * @return true если стек пустой, иначе false.
     */
    public boolean isEmpty() {
        return forwardLinked.isEmpty();
    }
}
