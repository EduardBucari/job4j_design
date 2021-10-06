package ru.job4j.gc;

/**
 * Понятие сборки мусора.
 * Для демонстрации сборки мусора и Метода finalize(), System.gc() и ключей запуска,
 * создаем дополнительный class GCDemo, который создает объекты без ссылок,
 * т.е. потенциально те, что нужно удалить.
 * Необходимо обратить внимание на метод info() в нем выводятся некоторые характеристики памяти:
 * - freeMemory(), возвращает количество свободной памяти в байтах.
 * - totalMemory(), возвращает общее количество памяти которое может быть использовано.
 * - maxMemory(), возвращает максимальное количество памяти которое может быть использовано.
 * Мы создаем в цикле 10000 объектов. Если запустить этот код, то можно увидеть, что происходит очистка мусора.
 *
 * Чтобы вызвать сборку мусора нужно вставить System.gc() после цикла.
 * Но виртуальная машина может это проигнорировать.
 * Запустите код, предварительно изменим 1000 на 10,
 * т.е. уменьшим количество создаваемых объектов.
 * Может быть, что при 10 не будет вызван сборщик мусора,
 * вы можете поэксперементировать с другими значениями.
 */

public class GCDemo {

    private static final long KB = 1000;
    private static final long MB = KB * KB;
    private static final Runtime ENVIRONMENT = Runtime.getRuntime();

    public static void info() {
        final long freeMemory = ENVIRONMENT.freeMemory();
        final long totalMemory = ENVIRONMENT.totalMemory();
        final long maxMemory = ENVIRONMENT.maxMemory();
        System.out.println("=== Environment state ===");
        System.out.printf("Free: %d%n", freeMemory / MB);
        System.out.printf("Total: %d%n", totalMemory / MB);
        System.out.printf("Max: %d%n", maxMemory / MB);
    }

    public static void main(String[] args) {
        info();
        // User user1 = new User("Evgeniy", 39);
        // User user2 = new User("Evgeniy Alekseevich Petrov", 23);
        for (int i = 0; i < 100; i++) {
            new Person(i, "N" + i);
            // new User("Evgeniy", 39);
        }
        System.gc();
        info();
    }
}
