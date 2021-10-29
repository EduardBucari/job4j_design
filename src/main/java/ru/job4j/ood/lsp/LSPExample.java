package ru.job4j.ood.lsp;

/**
 * Принцип подстановки Лисков.
 * LSP (Liskov Substitution Principle).
 * Данный принцип гласит, что если в коде используется сущность X,
 * то при постановке его наследников или других реализаций Y код будет работать.
 * Фактически этот принцип гарантирует, что не нарушиться принцип OCP относительно
 * взаимосвязи между классами в иерархии.
 *
 * Задание:
 * Придумайте 3 примера, когда происходит нарушение принципа LSP.
 *
 */
public class LSPExample {
    public int solution(int a, int b) {
        if (a < 0 || b < 0) {
            throw new IllegalStateException();
        }
        return a + b;
    }
}
    /**
     * Пример 1:
     * Нарушение LSP - предусловие усиленно в подклассе.
     */
    class ChildLSPExample extends LSPExample {

        @Override
        public int solution(int a, int b) {
            if (a < 10 || b < 10) {
                throw new IllegalArgumentException();
            }
            return a + b;
        }
    }




