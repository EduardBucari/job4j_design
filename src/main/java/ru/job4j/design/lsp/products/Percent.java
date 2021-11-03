package ru.job4j.design.lsp.products;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Выносим вычисление дней в процентах в отдельный класс Percent.
 * Процент вычисляется в зависимости от:
 *  daysCreateNow - дата производства продукта,
 *  dayCreateExpiry - срок годности продукта.
 */
public class Percent {
    public static double percentation(Food food) {
        double daysCreateNow = ChronoUnit.DAYS.between(LocalDate.now(), food.getExpiryDate());
        double dayCreateExpiry = ChronoUnit.DAYS.between(food.getCreateDate(), food.getExpiryDate());
        return 100 - (daysCreateNow * 100 / dayCreateExpiry);
    }
}
