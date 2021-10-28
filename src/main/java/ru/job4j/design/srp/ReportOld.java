package ru.job4j.design.srp;

import java.util.function.Predicate;

/**
 * Старая отчетность будет отображаться через class ReportOld
 */
public class ReportOld implements Report {

    @Override
    public String generate(Predicate<Employee> filter, Store store) {
        StringBuilder text = new StringBuilder();
        defaultGenerate(filter, store, text);
        return text.toString();
    }
}
