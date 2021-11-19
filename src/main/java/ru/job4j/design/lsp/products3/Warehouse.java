package ru.job4j.design.lsp.products3;

import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Storage<Food> {
    private final List<Food> foods = new ArrayList<>();

    @Override
    public boolean save(Food food) {
        if (quality(food)) {
            foods.add(food);
        }

        if (!save(food)) {
            return false;
        }
        foods.add(food);
        return true;
    }

    @Override
    public List<Food> getFoods() {
        return foods;
    }

    @Override
    public boolean quality(Food food) {
        return food.percent() < 25;
    }
}
