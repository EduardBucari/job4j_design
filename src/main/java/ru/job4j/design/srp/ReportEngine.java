package ru.job4j.design.srp;

import java.util.function.Predicate;

/**
 * Программисты реализовали систему отчетов в классе ReportEngine.
 * добавим поле private Report report;
 * и уберем StringBuilder.
 */
public class ReportEngine implements Report {
    private Store store;
    private Report report;

    public ReportEngine(Store store, Report report) {
        this.store = store;
        this.report = report;
    }

    @Override
    public String generate(Predicate<Employee> filter, Store store) {
        return report.generate(filter, store);
    }
}
