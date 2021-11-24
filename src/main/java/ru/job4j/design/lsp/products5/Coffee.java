package ru.job4j.design.lsp.products5;

import java.time.LocalDate;

/**
 *  Класс - Кофе.
 *
 * @author Eduard Bucari
 * @version 0.5
 * @since 24.11.2021
 */
public class Coffee extends Food {
    /**
     * Конструктор объекта типа Food.
     *
     * @param name       Название продукта.
     * @param createDate Дата производства.
     * @param expiryDate Срок годности.
     * @param price      Цена.
     * @param discount   Скидка.
     */
    public Coffee(String name, LocalDate createDate, LocalDate expiryDate,
                  double price, int discount) {
        super(name, createDate, expiryDate, price, discount);
    }
}
