package ru.job4j.design.lsp.products5;

import java.util.ArrayList;
import java.util.List;

/**
 * class Warehouse - "Склад".
 *
 * @author Eduard Bucari
 * @version 0.5
 * @since 24.11.2021
 */
public class Warehouse implements Storage {
    private final List<Food> foodList = new ArrayList<>();

    /**
     * Метод добавляет продукты на склад на основании ограничений.
     *
     * @param food Объект типа Food.
     * @return true в случае приёмки продукта в хранилище, иначе false.
     */
    @Override
    public boolean put(Food food) {
        if (accept(food)) {
            foodList.add(food);
            return true;
        }
        return false;
    }

    /**
     * Метод возвращает все продукты на складе, в виде списка.
     *
     * @return Список продуктов.
     */
    @Override
    public List<Food> get() {
        return new ArrayList<>(foodList);
    }

    /**
     * Метод проверяет ограничения продукта.
     *
     * @param food Объект типа Food.
     * @return true в случае добавления продукта на склад, иначе false.
     */
    public boolean accept(Food food) {
        return Percent.getPercentage(food) < 25;
    }
}
