package ru.job4j.collection.tree;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;
import java.util.function.Predicate;

/**
 * Создать элементарную структуру дерева.
 * Познакомимся с алгоритмом обхода дерева в ширину - breadth first search.
 * Задание.
 * 1. Допишите код в метод add().
 * 2. Дописать тест на отсутствие child в дереве.
 *    Если потомок присутствует в дереве, то метод add() должен возвращать false.
 * 3. Запустите тесты и проверьте работу дерева.
 */
  class SimpleTree<E> implements Tree<E> {
      private final Node<E> root;

      public SimpleTree(final E root) {
          this.root = new Node<>(root);
      }

    /**
     * Метод add - находит узел по значению parent и добавлять в него дочерний узел со значением child.
     * В этом методе нужно проверить, что значения child еще нет в дереве а parent есть.
     * Если child есть, то метод должен вернуть false.
     *
     * @param parent Значение узла-родителя к которому добавляем потомка.
     * @param child  Значение добавляемого потомка.
     * @return true в случае удачи, иначе false.
     */
      @Override
      public boolean add(E parent, E child) {
          boolean rsl = false;
          Optional<Node<E>> foundParent = findBy(parent);
          if (foundParent.isPresent() && findBy(child).isEmpty()) {
              rsl = foundParent.get().children.add(new Node<>(child));
          }
          return rsl;
      }

    /**
     * Метод findBy уже реализован и использует алгоритм обхода в ширину.
     */
      @Override
      public Optional<Node<E>> findBy(E value) {
          Optional<Node<E>> rsl = Optional.empty();
          Queue<Node<E>> data = new LinkedList<>();
          data.offer(this.root);
          while (!data.isEmpty()) {
              Node<E> el = data.poll();
              if (el.value.equals(value)) {
                  rsl = Optional.of(el);
                  break;
              }
              data.addAll(el.children);
          }
          return rsl;
      }
}
