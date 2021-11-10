package ru.job4j.design.lsp.parking2;

public class Truck2 implements Machine2 {

    private final int size;

    public Truck2(int size) {
        this.size = size;
    }

    public int getSize() {
        if (size > 1) {
            return size;
        }
        System.out.println("Размер грузовика указана не корректно");
        return 0;
    }
}
