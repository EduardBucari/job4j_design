package ru.job4j.design.lsp.products;

import ru.job4j.design.lsp.products.Food;
import ru.job4j.design.lsp.products.Store;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс Warehouse описывает добавление продукта в хранилище.
 */
public class Warehouse implements Store {
    private final List<Food> warehouseFoodList = new ArrayList<>();

    /**
     * Метод save() описывает добавление в хранилище при выполнении условия
     * @param food параметр на вход - продукт
     */
    @Override
    public void save(Food food) {
        if (condition(food)) {
            System.out.println("Go to the Warehouse !!!");
            warehouseFoodList.add(food);
        }
    }

    /**
     * выводим весь список продуктов в хранилище
     * @return на выходе список продуктов
     */
    @Override
    public List<Food> getAll() {
        return warehouseFoodList;
    }

    /**
     * Условие:
     * Если срок годности израсходован меньше чем на 25% направить в Warehouse;
     */
    @Override
    public boolean condition(Food food) {
        return Percent.percentation(food) < 25;
    }
}
