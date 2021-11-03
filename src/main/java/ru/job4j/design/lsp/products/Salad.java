package ru.job4j.design.lsp.products;

import ru.job4j.design.lsp.products.Food;

import java.time.LocalDate;

public class Salad extends Food {
    public Salad(String name, LocalDate expiryDate,
                 LocalDate createDate, double price, double discount) {
        super(name, expiryDate, createDate, price, discount);
    }
}
