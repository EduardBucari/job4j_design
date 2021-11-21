package ru.job4j.design.lsp.products4.store;

import ru.job4j.design.lsp.products4.food.Food;
import ru.job4j.design.lsp.products4.util.DateUtil;

public class Warehouse extends AbstractStore {
    @Override
    public boolean assume(Food food) {
        int percent = DateUtil.pastTimePercent(food.getCreateDate(), food.getExpiredDate());
        return percent < 25;
    }
}
