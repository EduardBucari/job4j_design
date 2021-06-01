package ru.job4j.collection.map;

import java.util.Objects;
import java.util.Calendar;

import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

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

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof User)) {
            return false;
        }
        final User other = (User) obj;
        return this.name.equals(other.name)
                && this.birthday.equals(other.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birthday);
    }

    @Override
    public String toString() {
        return String.format("{name: %s, birthday: %s.%s.%s, children: %s}",
                name,
                birthday.get(DAY_OF_MONTH),
                birthday.get(MONTH),
                birthday.get(YEAR),
                children
        );
    }
}
