package ru.job4j.serialization.json;

public class IdNumber {
    private final String idNumber;

    public IdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    @Override
    public String toString() {
        return "IdNumber{"
                +
                "idNumber='" + idNumber + '\''
                +
                '}';
    }
}
