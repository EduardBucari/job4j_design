package ru.job4j.design.lsp.products4.store;

import ru.job4j.design.lsp.products4.food.Food;
import java.util.List;

/**
 * Хранилище продуктов и Динамическое перераспределение продуктов
 */
public interface Store {
    boolean assume(Food food);
    void add(Food food);
    List<Food> clear();
}
