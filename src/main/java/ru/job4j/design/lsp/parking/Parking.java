package ru.job4j.design.lsp.parking;

import java.util.List;

/**
 * Интерфейс Parking - основа для создания
 * паркингов для машин различных рамеров.
 */
public interface Parking {
    boolean addCar(Car car);
    List<Car> getAll();
    int getFreePlaces(Car car);
}
