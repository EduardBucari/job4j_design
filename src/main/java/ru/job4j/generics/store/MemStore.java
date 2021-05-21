package ru.job4j.generics.store;

import java.util.ArrayList;
import java.util.List;

/**
 * Сделаем каркас универсального контейнера.
 * Задание:
 * 1. Реализуйте методы в классе MemStore.
 */
public final class MemStore<T extends Base> implements Store<T> {
    private final List<T> mem = new ArrayList<>();

    /**
     * Метод add - добавляет элементы в контейнер.
     * @param model - Добавляемый элемент.
     */
    @Override
    public void add(T model) {
        mem.add(model);
    }

    /**
     * Метод replace - производит замену элементов в контейнере.
     * @param id -  Идентификатор элемента в контейнере.
     * @param model - Новый элемент, отправляемый в контейнер.
     * @return - возвращает true в случае удачной замены, иначе false.
     */
    @Override
    public boolean replace(String id, T model) {
        boolean result = false;
        for (int i = 0; i < mem.size(); i++) {
            if (mem.get(i).getId().equals(id)) {
                mem.set(i, model);
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Метод delete - производит удаление элементов из контейнера.
     * @param id - Идентификатор удаляемого элемента.
     * @return - возвращает true в случае удачного удаления, иначе false.
     */
    @Override
    public boolean delete(String id) {
        return mem.removeIf(e -> e.getId().equals(id));
    }

    /**
     * Метод findById - производит поиск элементов по идентификатору в контейнере.
     * @param id - Идентификатор разыскиваемого элемента.
     * @return - возвращает найденный по идентификатору элемент.
     */
    @Override
    public T findById(String id) {
        return mem.stream()
                  .filter(e -> e.getId().equals(id))
                  .findFirst()
                  .orElse(null);
    }
}
