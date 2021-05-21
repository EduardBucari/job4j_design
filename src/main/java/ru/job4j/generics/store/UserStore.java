package ru.job4j.generics.store;

/**
 * Сделаем реализацию для пользователя. Будем использовать композицию объектов.
 * Задание:
 * 2. Реализуйте методы в классе UserStore.
 */
public class UserStore implements Store<User> {
    private final Store<User> store = new MemStore<>();

    /**
     * Метод add - добавляет элементы в контейнер.
     * @param model - Добавляемый элемент.
     */
    @Override
    public void add(User model) {
        store.add(model);
    }

    /**
     * Метод replace - производит замену элементов в контейнере.
     * @param id -  Идентификатор элемента в контейнере.
     * @param model - Новый элемент, отправляемый в контейнер.
     * @return - возвращает true в случае удачной замены, иначе false.
     */
    @Override
    public boolean replace(String id, User model) {
        return store.replace(id, model);
    }

    /**
     * Метод delete - производит удаление элементов из контейнера.
     * @param id - Идентификатор удаляемого элемента.
     * @return - возвращает true в случае удачного удаления, иначе false.
     */
    @Override
    public boolean delete(String id) {
        return store.delete(id);
    }

    /**
     * Метод findById - производит поиск элементов по идентификатору в контейнере.
     * @param id - Идентификатор разыскиваемого элемента.
     * @return - возвращает найденный по идентификатору элемент.
     */
    @Override
    public User findById(String id) {
        return store.findById(id);
    }
}


