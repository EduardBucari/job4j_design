package ru.job4j.design.lsp.products4.store;

import ru.job4j.design.lsp.products4.food.Food;
import ru.job4j.design.lsp.products4.util.DateUtil;

/**
 * Хранилище продуктов и Динамическое перераспределение продуктов
 */
public class Trash extends AbstractStore {
    @Override
    public boolean assume(Food food) {
        return DateUtil.pastTimePercent(food.getCreateDate(), food.getExpiredDate()) == 100;
    }
}
