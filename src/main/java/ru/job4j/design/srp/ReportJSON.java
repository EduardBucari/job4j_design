package ru.job4j.design.srp;

import com.google.gson.GsonBuilder;
import java.util.function.Predicate;

/**
 * Поддержка форматом XML, JSON в генераторе отчетов.
 * Задание 2:
 * 2. Добавить поддержку формата JSON в генераторе отчетов.
 * Для сериализации использовать классы, описанные в разделе IO.
 */
public class ReportJSON implements Report {
    private final Store store;

    public ReportJSON(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        var gson = new GsonBuilder().create();
        return gson.toJson(store.findBy(filter));
    }
}
