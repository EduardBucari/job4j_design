package ru.job4j.collection;

/**
 * Очередь на двух стеках.
 * В этом задании мы реализуем организацию данных - очередь.
 * Принцип очереди FIFO - first input first output. Первый пришел, первый ушел.
 * Метод poll() - должен возвращать первое значение и удалять его из коллекции.
 * Метод push(T value) - помещает значение в конец.
 * Для этого задания нужны два стека (in и out).
 */
public class SimpleQueue<T> {
    SimpleStack<T> in = new SimpleStack<>();
    SimpleStack<T> out = new SimpleStack<>();

    /**
     * Метод poll() - возвращает первое значение и удалять его из коллекции.
     * @return - первое значение.
     */
    public T poll() {
        if (out.isEmpty()) {
            while (!in.isEmpty()) {
                out.push(in.pop());
            }
        }
        return out.pop();
    }

    /**
     * Метод isEmpty() проверяет очередь на пустоту.
     * @return true если очередь пустая, иначе false.
     */
    public boolean isEmpty() {
        return in.isEmpty() && out.isEmpty();
    }

    /**
     * Метод push(T value) - помещает значение в конец очереди.
     * @param value - помещаемое значение в конец очереди.
     */
    public void push(T value) {
        in.push(value);
    }
}
