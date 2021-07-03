package ru.job4j.collection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Статистика по коллекции.
 * (Это задание сводиться к определению разницы между начальным состоянием массива и измененным).
 * Задание:
 * Решить задачу, реализовав метод diff()
 * Нужно реализовать класс Analysis и методы, которые
 * должен возвращать статистику об изменении коллекции:
 * - Метод List^User> previous - возвращает статистику по начальным данным,
 * - Метод List^User> current - возвращает статистику по измененным данным.
 *
 * Необходимо определить:
 * - Сколько добавлено новых пользователей.
 * - Сколько изменено пользователей.
 * (Изменённым считается объект в котором изменилось имя, а id осталось прежним).
 * - Сколько удалено пользователей.
 *
 * Обязательно напишите тесты.
 */
public class Analysis {

    /**
     * Метод diff проводит анализ входных данных.
     *
     * @param previous - начальные данные.
     * @param current  - измененные данные.
     * @return - результат анализа входных данных.
     */
    public Info diff(List<User> previous, List<User> current) {
        Info info = new Info();
        Map<Integer, String> prevMap = new HashMap<>();
        for (User pUser : previous) {
            prevMap.put(pUser.id, pUser.name);
        }
        for (User cUser : current) {
            if (!prevMap.containsKey(cUser.id)) {
                info.added++;
            } else if (!cUser.name.equals(prevMap.get(cUser.id))) {
                info.changed++;
            }
        }
        info.deleted = previous.size() - current.size() + info.added;
        return info;
    }

    /**
     * Модель данных User.
     */
    public static class User {
        int id;
        String name;

       /**
        * Добавляем Конструктор класса User.
        *
        * @param id   Идентификатор пользователя.
        * @param name Имя пользователя.
        */
      public User(int id, String name) {
        this.id = id;
        this.name = name;
      }
    }

    /**
     * Класс Info содержит данные анализа об удаленных,
     * измененных и удаленных пользователей.
     */
    public static class Info {
        int added;
        int changed;
        int deleted;
    }
}
