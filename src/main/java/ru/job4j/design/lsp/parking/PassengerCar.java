package ru.job4j.design.lsp.parking;

/**
 * Размер легкового автомобиля всегда равен 1.
 */
public class PassengerCar extends Car {
   public static final int AUTO_SIZE = 1;
   public PassengerCar(String name) {
       super(name);
   }

   @Override
    public int getSize() {
       return AUTO_SIZE;
   }
}
