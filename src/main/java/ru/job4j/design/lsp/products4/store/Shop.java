package ru.job4j.design.lsp.products4.store;

import ru.job4j.design.lsp.products4.food.Food;
import ru.job4j.design.lsp.products4.util.DateUtil;

/**
 * Хранилище продуктов и Динамическое перераспределение продуктов
 */
public class Shop extends AbstractStore {
    @Override
    public boolean assume(Food food) {
        int percent = DateUtil.pastTimePercent(food.getCreateDate(), food.getExpiredDate());
        if (percent > 75) {
            food.setDiscount(30);
        }
        return percent >= 25 && percent < 100;
    }
}
