package ru.job4j.collection.map;

import java.util.*;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

/**
 * Переопределить только equals
 * Задание:
 * 1. В классе User переопределить только метод equals(), метод hasCode() не переопределять.
 * 2. Создать два объекта User, которые имеют одинаковые поля.
 *    Убедитесь что вы передаете одну и ту же дату рождения, вплоть до миллисекунд.
 * 3. Создать карту Map^User, Object>
 * 4. Добавить две пары. В качестве ключей использовать объекты User из пункта 2,
 *    а в качестве значения new Object().
 *    Вывести карту на печать.
 *    Описать полученный результат словами.
 */
public class UsageUser {
    public static void main(String[] args) {
        User user1 = new User("Alex",
                new GregorianCalendar(1999, Calendar.SEPTEMBER, 19), 1);
        User user2 = new User("Alex",
                new GregorianCalendar(1999, Calendar.SEPTEMBER, 19), 1);

        Map<User, Object> map = new HashMap<>();
        map.put(user1, new Object());
        map.put(user2, new Object());
        System.out.println(map.get(user1));
        System.out.println(map.get(user2));

    }
}
