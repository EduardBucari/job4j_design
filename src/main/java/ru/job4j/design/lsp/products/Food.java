package ru.job4j.design.lsp.products;

import java.time.LocalDate;
import java.util.Objects;

/**
 *  Хранилище продуктов.
 *  Часть 1.
 *  Задание:
 *  1. Создать класс (модель данных) Food с полями: name, expiryDate, createDate, price, discount.
 *     На основе класса Food создать другие продукты.
 *     (Создадим дополнительные классы: Milk, Salad, SourCream)
 *  2. Создать классы хранилище продуктов Warehouse, Shop, Trash.
 *     (Данные классы будем создавать на основе интерфейса Store).
 *  3. Создать класс обработчик перераспределения продуктов в место использования ControlQuality.
 *    Класс должен перераспределять еду по хранилищам в зависимости от условий:
 *      3.1. Если срок годности израсходован меньше чем на 25% направить в Warehouse;
 *      3.2. Если срок годности от 25% до 75% направить в Shop;
 *      3.3. Если срок годности больше 75% то выставить скидку на продукт и отправить в Shop;
 *      3.4. Если срок годности вышел. Отправить продукт в мусорку.
 *
 * В данной задаче надо использовать шаблон проектирования strategy pattern.
 * Нельзя использовать instanceOf или if ("Shop".equals(storage.getName())
 */
public class Food {
    protected String name;
    protected LocalDate expiryDate;
    protected LocalDate createDate;
    protected double price;
    protected double discount;

    public Food() {
    }

    public Food(String name, LocalDate expiryDate, LocalDate createDate, double price, double discount) {
        this.name = name;
        this.expiryDate = expiryDate;
        this.createDate = createDate;
        this.price = price;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Food food = (Food) o;
        return Double.compare(food.price, price) == 0
                && Double.compare(food.discount, discount) == 0
                && Objects.equals(name, food.name)
                && Objects.equals(expiryDate, food.expiryDate)
                && Objects.equals(createDate, food.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, expiryDate, createDate, price, discount);
    }

    @Override
    public String toString() {
        return "Food{"
                + "name='" + name + '\''
                + ", expiryDate=" + expiryDate
                + ", createDate=" + createDate
                + ", price=" + price
                + ", discount=" + discount
                + '}';
    }
}
