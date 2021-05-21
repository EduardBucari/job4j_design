package ru.job4j.generics.store;

/**
 * Задание:
 * 3. Создайте и реализуйте класс RoleStore.
 *
 * Реализуем роли(методы), описанные в Interface Store, (используем композицию объектов):
 * 1. Метод add - добавляет элементы в контейнер.
 * 2. Метод replace - производит замену элементов в контейнере.
 * 3. Метод delete - производит удаление элементов из контейнера.
 * 4. Метод findById - производит поиск элементов по идентификатору в контейнере.
 */
public class RoleStore implements Store<Role> {
    private final Store<Role> store = new MemStore<>();

    /**
     * Метод add - добавляет элементы в контейнер.
     * @param model - Добавляемый элемент.
     */
    @Override
    public void add(Role model) {
        store.add(model);
    }

    /**
     * Метод replace - производит замену элементов в контейнере.
     * @param id -  Идентификатор элемента в контейнере.
     * @param model - Новый элемент, отправляемый в контейнер.
     * @return - возвращает true в случае удачной замены, иначе false.
     */
    @Override
    public boolean replace(String id, Role model) {
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
    public Role findById(String id) {
        return store.findById(id);
    }
}
