package ru.job4j.design.lsp.parking2;

public class Truck2 implements Machine2 {

    private final int size;

    public Truck2(int size) {
        this.size = size;

        if (size <= Car2.SIZE) {
            throw new IllegalArgumentException(
                    "Invalid truck size. Size must be more than 1");
        }
    }

    public int getSize() {
        return size;
    }
}
