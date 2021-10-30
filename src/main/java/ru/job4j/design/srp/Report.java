package ru.job4j.design.srp;

import java.util.function.Predicate;

/**
 * Система отчета представлена через интерфейс Report.
 * Ее необходимо доработать.
 */
public interface Report {
    String generate(Predicate<Employee> filter);
}
