package ru.job4j.generics.store;

/**
 * Реализовать Store^T extends Base>;
 * Все модели наследуются от базовой модели ru.job4j.generic.store.Base
 * В этом задании необходимо реализовать контейнеры для хранения объектов.
 * Структура контейнеров будет одинаковая.
 * Все ограничения хранимых типов мы должны задать с помощью Generics.
 *
 *   Базовые модели данных:
 * - Контейнеры должны быть описаны интерфейсом: interface Store;
 * - Универсальный контейнер: class MemStore;
 * - Модель данных пользователя контейнера: class UserStore;
 * - Класс User, наследник от Base;
 * - Логика основных действий в контейнере:  class RoleStore;
 * - Класс Role, наследник от Base;
 *
 * Задание:
 * 1. Реализуйте методы в классе MemStore.
 * 2. Реализуйте методы в классе UserStore.
 * 3. Создайте и реализуйте класс RoleStore.
 *
 * Важно! Обратите внимание, что id имеет строковый тип.
 * Это значит id и индекс в списке это разные вещи!
 * Нельзя делать так store.get(Integer.valueOf(id));
 */
public abstract class Base {
    private  final String id;

    public Base(final String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
