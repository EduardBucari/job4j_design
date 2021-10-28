package ru.job4j.ood.ocp;

import java.util.ArrayList;

/**
 *  Принцип открытости закрытости.
 *  OCP – Open Closed Principle.
 *  Данный принцип гласит, что программные сущности должны
 *  быть открыты к расширению, но закрыты к изменению.
 *  Задание:
 *  Придумайте 3 примера, когда происходит нарушение принципа OCP.
 *
 *  Пример 1 нарушение OCP
 *     - был класс Example и по прошествии времени
 *     понадобилось добавить в него функционал возведения в степень,
 *     пришлось добавить в класс еще один метод -
 *     public int calculatePow(int a, int b) {
 *         return Math.pow(a, b);
 *     }
 *     это нарушение OCP - класс должен быть закрыт для изменения.
 */
public class ExampleOCP {
    public int calculatePlus(int a, int b) {
        return a + b;
    }

    public int calculateMinus(int a, int b) {
        return a - b;
    }

    public int calculateMulti(int a, int b) {
        return a * b;
    }

    public int calculateDivide(int a, int b) {
        return a / b;
    }


    /*
     Пример 2 Нарушение OCP
     - поля должны представлять сабой тип абстракций, а не
       конкретной реализации.
    */
    class ExampleOCP2 {
        ArrayList<Integer> arrayList;

        public ExampleOCP2(ArrayList<Integer> arrayList) {
            this.arrayList = arrayList;
        }
    }

    /*
     Пример 3 нарушение OCP
    - параметры метода и возвращаемые типы должны
      быть абстракциями, а не конкретными реализациями.
     */
    class ExampleOCP3 {
        public ArrayList<Integer> sort(ArrayList<Integer> arrayList) {
            /*
            Some logic
             */
            return arrayList;
        }
    }
}
