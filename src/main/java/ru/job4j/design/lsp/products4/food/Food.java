package ru.job4j.design.lsp.products4.food;

import java.time.LocalDateTime;

/**
 * Хранилище продуктов и Динамическое перераспределение продуктов
 */
public class Food {
    private String name;
    private LocalDateTime expiredDate;
    private LocalDateTime createDate;
    private float price;
    private float discount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(LocalDateTime expiredDate) {
        this.expiredDate = expiredDate;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }
}
