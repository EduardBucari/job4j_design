package ru.job4j.design.lsp.parking;

import java.util.ArrayList;
import java.util.List;

/**
 * class TruckParking реализует интерфейс в классе грузовиков.
 */
public class TruckParking implements Parking {
    private int truckParkingSize;

    private final List<Car> truckParkingList = new ArrayList<>();

    public TruckParking(int truckParkingSize) {
        this.truckParkingSize = truckParkingSize;
    }

    @Override
    public boolean addCar(Car car) {
        boolean result = false;
        if (car.getSize() > 1 && truckParkingSize >= 1) {
            truckParkingList.add(car);
            truckParkingSize--;
            result = true;
        }
        return result;
    }

    @Override
    public List<Car> getAll() {
        return new ArrayList<>(truckParkingList);
    }

    @Override
    public int getFreePlaces(Car car) {
        return truckParkingSize;
    }
}
