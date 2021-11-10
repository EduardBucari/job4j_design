package ru.job4j.design.lsp.parking;

import java.util.List;

/**
 * class Distribution описывает механизм
 * распределения машин по паркингам.
 */
public class Distribution {
    List<Parking> parkingList;
    Parking truck;
    Parking passenger;

    public Distribution(List<Parking> parkingList) {
        this.parkingList = parkingList;
    }

    public boolean distribute(List<Car> carList) {
        boolean result = true;
        for (Car car : carList) {
            int size = car.getSize();
            if (size > 1) {
                if (truck.getFreePlaces(car) >= 1) {
                    result = truck.addCar(car);
                } else {
                    result = passenger.addCar(car);
                }
            } else if (size == 1) {
                result = passenger.addCar(car);
            }
        }
        return result;
    }
}
