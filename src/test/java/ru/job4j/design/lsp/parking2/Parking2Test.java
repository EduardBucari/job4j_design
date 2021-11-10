package ru.job4j.design.lsp.parking2;

import org.junit.Assert;
import org.junit.Test;

public class Parking2Test {

    @Test
    public void whenAdd() {
        Parking2 parking = new Parking2(1, 1);
        Machine2 car = new Car2();
        Machine2 truck = new Truck2(2);
        parking.add(car);
        Assert.assertTrue(parking.add(truck));
    }

    @Test
    public void whenAddTwo() {
        Parking2 parking = new Parking2(4, 2);
        Machine2 truck = new Truck2(2);
        parking.add(truck);
        parking.add(truck);
        parking.add(truck);
        Assert.assertTrue(parking.add(truck));
    }

    @Test
    public void whenAddTree() {
        Parking2 parking = new Parking2(4, 0);
        Machine2 truck = new Truck2(2);
        parking.add(truck);
        parking.add(truck);
        parking.add(truck);
        Assert.assertFalse(parking.add(truck));
    }

    @Test
    public void whenAddFour() {
        Parking2 parking = new Parking2(4, 1);
        Machine2 truck = new Truck2(3);
        Machine2 car = new Car2();
        parking.add(truck);
        parking.add(truck);
        Assert.assertTrue(parking.add(car));
    }

    @Test
    public void whenAddFive() {
        Parking2 parking = new Parking2(5, 3);
        Machine2 truck = new Truck2(3);
        Machine2 truck1 = new Truck2(4);
        Machine2 car = new Car2();
        parking.add(truck);
        parking.add(truck1);
        Assert.assertTrue(parking.add(car));
    }
}