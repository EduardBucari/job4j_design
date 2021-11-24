package ru.job4j.design.lsp.products5;

import java.util.List;

/**
 * Класс - "Контроль качества".
 *
 * @author Eduard Bucari
 * @version 0.5
 * @since 24.11.2021
 */
public class ControlQuality {
    /**
     * Список возможных хранилищ.
     */
    private final List<Storage> storageList;

    public ControlQuality(List<Storage> storageList) {
        this.storageList = storageList;
    }

    /**
     * Метод распределяет продукт по хранилищам.
     *
     * @param food Продукт на распределение.
     */
    public void distribute(Food food) {
        storageList.forEach(s -> s.put(food));
    }
}
