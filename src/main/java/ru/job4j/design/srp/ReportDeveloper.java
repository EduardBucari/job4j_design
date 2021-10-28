package ru.job4j.design.srp;

import java.util.function.Predicate;

/**
 * - Через месяц применения системы отчетности отдел программистов потребовал ответы в виде html.
 *   (Для реализации этого требования создадаим class ReportDeveloper)
 */
public class ReportDeveloper implements Report {

    @Override
    public String generate(Predicate<Employee> filter, Store store) {
        StringBuilder text = new StringBuilder();
        text.append("<!doctype html>"
                + "<head>"
                + "  <meta charset=\"utf-8\">"
                + "  <title>Job4j</title>"
                + "  </head>"
                + "<body>");
        text.append("<table>"
                + "<thead>"
                + "  <tr>"
                + "    <th>Name</th>"
                + "    <th>Hired</th>"
                + "    <th>Fired</th>"
                + "    <th>Salary</th>"
                + "  </tr>"
                + "</thead>"
                + "<tbody>");
        defaultGenerate(filter, store, text);
        text.append("</tbody>"
                + "</table>"
                + "</body>"
                + "</html>");
        return text.toString();
    }
}
