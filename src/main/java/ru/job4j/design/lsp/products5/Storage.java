package ru.job4j.design.lsp.products5;

import java.util.List;

/**
 * interface Storage - Абстрактное хранилище.
 *
 * @author Eduard Bucari
 * @version 0.5
 * @since 24.11.2021
 */
public interface Storage {
    /**
     * Добавить продукт в хранилище.
     *
     * @param food Объект типа Food.
     * @return true в случае приёмки продукта в хранилище, иначе false.
     */
    boolean put(Food food);

    /**
     * Вернуть список продуктов из хранилища.
     *
     * @return Список объектов типа Food.
     */
    List<Food> get();

    /**
     * Ограничения для хранения продукта в хранилище.
     *
     * @param food Объект типа Food.
     * @return true если продукт добавлен в хранилище, иначе false.
     */
    boolean accept(Food food);
}
