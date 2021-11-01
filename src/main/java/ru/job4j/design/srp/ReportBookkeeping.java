package ru.job4j.design.srp;

import java.util.function.Predicate;

/**
 * - Отдел бухгалтерии попросил изменить вид зарплаты.
 *   (Для реализации этого требования создадаим class ReportBookkeeping)
 */
public class ReportBookkeeping implements Report {
    private Store store;

    public ReportBookkeeping(Store store) {
        this.store = store;
    }

    /**
     * метод generate() описывает получение отчета для бухгалтерии
     * @param filter на входе используется фильтр
     * @return на выходе фильтрованный отчет
     * Отдел бухгалтерии попросил изменить вид зарплаты (в долларах).
     * .append(employee.getSalary() / 71).append(";") поменяли
     */
    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; SalaryInDollar;");
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(employee.getSalary() / 71).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
