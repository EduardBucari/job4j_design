package ru.job4j.design.lsp.products;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс Shop описывает добавление продуктов в магазин.
 */
public class Shop implements Store {

    private final List<Food> shopFoodList = new ArrayList<>();

    int discount = 50;

    @Override
    public void save(Food food) {
        if (condition(food)) {
            shopFoodList.add(food);
        }
    }

    @Override
    public List<Food> getAll() {
        return shopFoodList;
    }

    /**
     * Условия:
     * 1. Если срок годности от 25% до 75% направить в Shop;
     * 2. Если срок годности больше 75% то выставить скидку на продукт и отправить в Shop;
     */
    @Override
    public boolean condition(Food food) {

        boolean result = false;
        if (Percent.percentation(food) >= 25 && Percent.percentation(food) <= 75) {
            System.out.println("Product go to Shop !!!");
            result = true;
        }
        if (Percent.percentation(food) > 75 && Percent.percentation(food) < 100) {
            food.setDiscount(discount);
            System.out.println("Discount price !!!");
            result = true;
        }
        return result;
    }
}
