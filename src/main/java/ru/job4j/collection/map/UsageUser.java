package ru.job4j.collection.map;

import java.util.*;


import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

/**
 * Без переопределения equals и hashCode.
 * Задание:
 * 1. В классе User не переопределять методы equals() и hashCode();
 * 2. Создать два объекта User, которые имеют одинаковые поля.
 * 3. Создать карту Map^User, Object>
 * 4. Добавить две пары. В качестве ключей использовать объекты User из пункта 2,
 *    а в качестве значения new Object().
 *    Вывести карту на печать.
 *    Описать полученный результат словами.
 */
public class UsageUser {
    public static void main(String[] args) {
        User user1 = new User("David",
                new GregorianCalendar(1999, Calendar.SEPTEMBER, 19), 2);
        User user2 = new User("Alex",
                new GregorianCalendar(1997, Calendar.OCTOBER, 25), 1);

        Map<User, Object> map = new HashMap<>();
        map.put(user1, new Object());
        map.put(user2, new Object());
        System.out.println(map.get(user1));
        System.out.println(map.get(user2));
    }

}
