package ru.job4j.design.lsp.products2;

import java.time.LocalDate;

public class Milk extends Food {

    public Milk() {
    }

    public Milk(String name, LocalDate expiryDate,
                LocalDate createDate, double price, double discount) {
        super(name, expiryDate, createDate, price, discount);
    }
}
