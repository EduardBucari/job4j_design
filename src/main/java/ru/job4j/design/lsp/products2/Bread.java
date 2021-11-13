package ru.job4j.design.lsp.products2;

import java.time.LocalDate;

public class Bread extends Food {

    public Bread() {
    }

    public Bread(String name, LocalDate expiryDate,
                 LocalDate createDate, double price, double discount) {
        super(name, expiryDate, createDate, price, discount);
    }
}