package ru.job4j.design.lsp.parking;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class DistributionTest {
    PassengerCar passengerCar;
    Truck truck;

    @Test
    public void parkingTruck() {
        List<Car> carList = new ArrayList<>();
        Parking parking = new TruckParking(3);
        Car car = new Truck("Volvo", 2);
        carList.add(car);
        Assert.assertTrue(parking.addCar(car));
        Assert.assertEquals(carList, parking.getAll());
    }

    @Test
    public void fillPassParking() {
        List<Car> carList = new ArrayList<>();
        Parking parking = new PassCarParking(2);
        Car car1 = new PassengerCar("dodge");
        Car car2 = new PassengerCar("toyota");
        carList.add(car1);
        carList.add(car2);
        Assert.assertTrue(parking.addCar(car1));
        Assert.assertTrue(parking.addCar(car2));
        Assert.assertEquals(carList, parking.getAll());

    }

    @Test
    public void fillTruckParking() {
        List<Car> carList = new ArrayList<>();
        Parking parking = new TruckParking(6);
        Car car = new Truck("Volvo", 2);
        Car car2 = new Truck("Volvo", 2);
        Car car3 = new Truck("Volvo", 2);
        carList.add(car);
        carList.add(car2);
        carList.add(car3);
        Assert.assertTrue(parking.addCar(car));
        Assert.assertTrue(parking.addCar(car2));
        Assert.assertTrue(parking.addCar(car3));
        Assert.assertEquals(carList, parking.getAll());
    }
}