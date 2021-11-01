package ru.job4j.design.srp;

import java.util.function.Predicate;

/**
 * - Через месяц применения системы отчетности отдел программистов потребовал ответы в виде html.
 *   (Для реализации этого требования создадаим class ReportDeveloper)
 */
public class ReportDeveloper implements Report {
    private Store store;

    public ReportDeveloper(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("<!DOCTYPE HTML>")
                .append("<html><body><table>")
                .append("<tr><td>Name</td>")
                .append("<td>Hired</td>")
                .append("<td>Fired</td>")
                .append("<td>Salary</td></tr>");
        for (Employee employee : store.findBy(filter)) {
            text.append("<tr><td>").append(employee.getName()).append("</td>")
                    .append("<td>").append(employee.getHired()).append("</td>")
                    .append("<td>").append(employee.getFired()).append("</td>")
                    .append("<td>").append(employee.getSalary()).append("</td></tr>")
                    .append(System.lineSeparator());
        }
        text.append("</table></body></html>");
        return text.toString();
    }
}
