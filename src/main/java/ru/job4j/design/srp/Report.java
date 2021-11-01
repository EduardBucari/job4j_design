package ru.job4j.design.srp;

import java.util.function.Predicate;

/**
 * Система отчета представлена через интерфейс Report.
 * Ее необходимо доработать.
 * В компании есть три департамента: бухгалтерия, программисты, HR.
 * и Всем департаментам нужны отчеты.
 */
public interface Report {
    String generate(Predicate<Employee> filter);
}
