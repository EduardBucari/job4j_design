package ru.job4j.design.lsp.parking;

import java.util.Objects;

/**
 * Парковка машин.
 * Задание:
 * Необходимо разработать сервис для учета парковки машин.
 *
 * Существует парковка для грузовых и легковых машин.
 * Количество парковочных мест для каждого типа машин задается на этапе создания парковки.
 *
 * Легковая машина может занять только место, предназначенное для легковой машины.
 * Грузовая машина может разместиться на месте, предназначенном для грузовых машин,
 * либо на N парковочных мест для легковых машин, стоящих рядом.
 *
 * Важно! Легковой считается машина у которой размер равен 1, а грузовой у которой размер > 1.
 *
 * Разбейте реализацию этой задачи на 3 этапа:
 * 1. Создание интерфейсов и схемы взаимодействия этих интерфейсов.
 * 2. Реализация тестов.
 * 3. Реализация кода.
 * Важно. Каждый этап сдавать по отдельности.
 *
 * class Car - Модель данных машин.
 * class Car надо сделать абстрактным так как от него идет наследование.
 */
public abstract class Car {
    private String name;
    private int size;

    public Car(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public Car(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return size == car.size && Objects.equals(name, car.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, size);
    }

    @Override
    public String toString() {
        return "Car{"
                + "name='" + name + '\''
                + ", size=" + size
                + '}';
    }
}
