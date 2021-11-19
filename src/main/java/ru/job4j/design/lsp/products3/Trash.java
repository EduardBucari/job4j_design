package ru.job4j.design.lsp.products3;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class Trash implements Storage<Food> {
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
        Calendar now = Calendar.getInstance();
        now.setTimeZone(TimeZone.getDefault());
        return now.after(food.getExpiryDate());
    }
}
