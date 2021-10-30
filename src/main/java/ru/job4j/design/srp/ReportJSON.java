package ru.job4j.design.srp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.function.Predicate;

/**
 * Поддержка форматом XML, JSON в генераторе отчетов.
 * Задание 2:
 * 2. Добавить поддержку формата JSON в генераторе отчетов.
 * Для сериализации использовать классы, описанные в разделе IO.
 */
public class ReportJSON implements Report {
    private Store store;

    public ReportJSON(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        Gson gson = new GsonBuilder().create();
        for (Employee e : store.findBy(filter)) {
            text.append(gson.toJson(e));
            text.append(System.lineSeparator());
        }
        return text.toString();
    }
}
