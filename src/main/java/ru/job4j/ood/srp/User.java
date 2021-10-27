package ru.job4j.ood.srp;

import java.util.Objects;

/**
 * Принцип единственной ответственности.
 * Задание:
 * Придумать примеры на нарушение принципа SRP
 * Один из принцыпов SOLID - Single Responsibility Principle
 * Каждый класс должен отвечать только за представления своего функционала,
 *
 * Представлена модель данных User, в которой не должна быть прописана логика
 * сохранения пользователя и загрузки пользовательской информации из базы данных.
 * Данная логика должна прописана в другом специальном классе.
 */
public class User {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return age == user.age && Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    /*
     Метод saveToBD прописывает логику сохранения пользователя в базу данных.
     Данное действие нарушает Принцип единственной ответственности.
     */
    public void saveToBD() {

    }

    /*
     Метод loadFromDB прописывает логику загрузки пользовательской информации из базы данных.
     Данное действие так же нарушает Принцип единственной ответственности
     */
    public void loadFromDB() {

    }
}
