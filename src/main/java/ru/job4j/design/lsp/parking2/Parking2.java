package ru.job4j.design.lsp.parking2;

public class Parking2 implements ParkingMachine2 {

    private int parkingSpaceCar;
    private int parkingSpaceTruck;

    /**
     * @param parkingSpaceCar   - колличество легковых машин
     * @param parkingSpaceTruck - колличество грузовых машин
     */
    public Parking2(int parkingSpaceCar, int parkingSpaceTruck) {
        this.parkingSpaceCar = parkingSpaceCar;
        this.parkingSpaceTruck = parkingSpaceTruck;
    }

    @Override
    public boolean add(Machine2 machine2) {
        if (machine2.getSize() == Car2.SIZE) {
            return parkingCar(machine2);
        } else if (machine2.getSize() > Car2.SIZE) {
            return parkingTruck(machine2);
        }
        return false;
    }

    private boolean parkingTruck(Machine2 machine2) {
        if (parkingSpaceTruck >= Car2.SIZE) {
            parkingSpaceTruck--;
            return true;
        } else if (parkingSpaceCar >= machine2.getSize()) {
            parkingSpaceCar = parkingSpaceCar - machine2.getSize();
            return true;
        }
        return false;
    }

    private boolean parkingCar(Machine2 machine2) {
        if (parkingSpaceCar >= machine2.getSize()) {
            parkingSpaceCar--;
            return true;
        }
        return false;
    }
}
