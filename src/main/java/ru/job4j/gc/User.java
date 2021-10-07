package ru.job4j.gc;

/**
 * @author Eduard Bucari
 * @version 0.1
 * @since 07/10/2021
 */
public class User {
    String firstName;
    String lastName;
    int age;
    int id;

    public User(String firstName, String lastName, int age, int id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.id = id;
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void finalize() {
        System.out.printf("Removed: %d %s%n", id, lastName);
    }
}
