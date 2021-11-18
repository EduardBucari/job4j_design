package ru.job4j.design.lsp.products3;

import java.util.List;

public interface Storage<T extends Food> {
    void save(T food);
    List<T> getFoods();
    boolean quality(T food);
}
