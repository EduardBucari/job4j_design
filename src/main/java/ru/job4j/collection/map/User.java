package ru.job4j.collection.map;

import java.util.Objects;
import java.util.Calendar;

/**
 * Создать модель User.
 * User содержит три поля String name, int children, Calendar birthday.
 * Поля должны быть приватными. Добавьте конструктор.
 *
 * Дополнительно, стандартные процедуры:
 * - Реализовать гетеры и сетеры
 * - Переопределить методы equals(Object o) и hashCod().
 * - Переопределить метод toString();
 * Импортировать Objects и полный Calendar.
 */
public class User {
    private final String name;
    private final Calendar birthday;
    private int children;

    public User(String name, Calendar birthday, int children) {
        this.name = name;
        this.birthday = birthday;
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    // Переопределить в классе User метод hashCode(), метод equals() не переопределять.
    @Override
    public int hashCode() {
        return Objects.hash(name, birthday, children);
    }
}
