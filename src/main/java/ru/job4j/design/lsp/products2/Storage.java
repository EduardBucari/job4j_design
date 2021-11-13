package ru.job4j.design.lsp.products2;

import java.util.List;

public interface Storage {
    boolean accept(Food food);

    void addToStorage(Food food);

    List<Food> getFoods();
}