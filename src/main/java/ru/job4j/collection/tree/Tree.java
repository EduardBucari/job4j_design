package ru.job4j.collection.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 1. Для начла создадим интерфейс описывающий наше дерево.
 *
 * Класс Node описывает узел дерева.
 * Узел содержит хранимое значение и ссылки на дочерние узлы.
 *
 * Метод add - Должен находить узел по значению parent и
 * добавлять в него дочерний узел со значением child.
 * В этом методе нужно проверить, что значения child еще нет в дереве а parent есть.
 * Если child есть, то метод должен вернуть false.
 * @param <E> элемент или узел дерева.
 */
public interface Tree<E> {

    boolean add(E parent, E child);

    Optional<Node<E>> findBy(E value);

    class Node<E> {
        final E value;
        final List<Node<E>> children = new ArrayList<>();

        public Node(E value) {
            this.value = value;
        }
    }
}
