package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ru.job4j.serialization.Contact;

import java.util.Arrays;

/**
 * Формат JSON
 * Пакет классов работающих с Json форматом обмена данных.
 * Задание:
 * 1) Придумайте Java объект, объект должен иметь поля булево,
 *    какой-нибудь числовой тип, строковый тип, вложенный объект и массив.
 * 2) Опишите его представление в формате JSON в комментарии к заданию.
 * 3) Подключите библиотеку Gson, преобразуйте объект вашего класса в json-строку и обратно.
 */
public class Person {
    private final boolean sex;
    private final int age;
    private final Contact contact;
    private final String[] statuses;

    public Person(boolean sex, int age, Contact contact, String... statuses) {
        this.sex = sex;
        this.age = age;
        this.contact = contact;
        this.statuses = statuses;
    }

    @Override
    public String toString() {
        return "Person{"
                + "sex=" + sex
                + ", age=" + age
                + ", contact=" + contact
                + ", statuses=" + Arrays.toString(statuses)
                + '}';
    }

    /**
     * Реализация Json.
     * @param args Аргументы командной строки.
     */
    public static void main(String[] args) {
        final Person person = new Person(false, 40, new Contact(
                        123456, "+7 (111) 111-11-11"), "IT Programmer", "Married");

        /* Преобразуем объект person в json-строку. */
        final  Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(person));

        /* Модифицируем json-строку*/
        final String personJson =
                "{"
                        + "\"sex\":false," // "sex" : false, пара «ключ: значение» с логическим типом
                        + "\"age\":35,"    // "age" : 30,    пара «ключ: значение» с числовым типом
                        + "\"contact\":"   // "contact" : {  объект
                        + "{"
                        + "\"phone\":\"+7 (111) 111-11-12\"" // "phone" : "11-12" пара «ключ: значение» со строкой
                        + "},"
                        + "\"statuses\":" // "statuses":["IT Programmer","Married"] // массив строк
                        + "[\"Student\",\"Free\"]"
                        + "}";
        final Person personMod = gson.fromJson(personJson, Person.class);
        System.out.println(personMod);
    }
}
