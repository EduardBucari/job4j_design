package ru.job4j.design.lsp.products;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс Trash описывает добавление продукта на мусорку.
 */
public class Trash implements Store {
    private final List<Food> trashFoodList = new ArrayList<>();

    /**
     * Условие:
     * Если срок годности вышел. Отправить продукт в мусорку.
     */
    @Override
    public void save(Food food) {
        if (condition(food)) {
            System.out.println("Product go to Trash !!!");
            trashFoodList.add(food);
        }
    }

    @Override
    public List<Food> getAll() {
        return trashFoodList;
    }

    @Override
    public boolean condition(Food food) {
        return LocalDate.now().isAfter(food.getExpiryDate());
    }
}
