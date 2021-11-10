package ru.job4j.design.lsp.products;

import ru.job4j.design.lsp.products.Food;

import java.util.List;

/**
 * Интерфейс Store описывающий хранилище
 */
public interface Store {
    boolean condition(Food food);

    void save(Food food);

    List<Food> getAll();
}
