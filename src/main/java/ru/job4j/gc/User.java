package ru.job4j.gc;


/**
 * Демонстрация работы GC.
 * Задание:
 * 1. Создать объект User c полями и перекрытым методом finalize.
 * 2. Создать несколько объектов User и руками рассчитать сколько они будет занимать памяти.
 *    Запрещено использовать библиотеку carrotsearch. Рассчитать надо теоретически.
 * 3. Нужно найти информацию. Сколько памяти занимает пустой объект без полей.
 * 4. Добиться состояния, когда виртуальная машины вызывает сборщик мусора самостоятельно. За счет ключей xmx.
 * 5. Объяснить поведение программы в текстовом файле.
 *
 * @author Eduard Bucari
 * @version 0.1
 * @since 07/10/2021
 */
public class User {
    String firstName;
    String lastName;
    int age;
    int id;

    public User(String firstName, String lastName, int age, int id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.id = id;
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void finalize() {
        System.out.printf("Removed: %d %s%n", id, lastName);
    }
}
