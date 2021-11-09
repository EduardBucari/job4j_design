package ru.job4j.design.lsp.products;

import java.util.List;

/**
 * Класс ControllQuality является обработчиком перераспределения продуктов в место использования.
 * Класс должен перераспределять еду по хранилищам в зависимости от следующих условий:
 *       1. Если срок годности израсходован меньше чем на 25% направить в Warehouse;
 *       2. Если срок годности от 25% до 75% направить в Shop;
 *       3. Если срок годности больше 75% то выставить скидку на продукт и отправить в Shop;
 *       4. Если срок годности вышел. Отправить продукт в мусорку.
 */
public class ControllQuality {
    private final List<Store> storeList;

    public ControllQuality(List<Store> storeList) {
        this.storeList = storeList;
    }

    public boolean distribution(Food food) {
        for (Store store : storeList) {
            store.save(food);
        }
        return true;
    }
}
