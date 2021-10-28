package ru.job4j.design.srp;

import java.util.Calendar;
import java.util.Objects;

/**
 * Отчеты.
 * В компании есть необходимость в генерации отчетов.
 * Есть база данных со всеми сотрудниками компании.
 * - Сотрудники описываются  в модели данных class Employee.
 * - Доступ к базе данных осуществляется через интерфейс Store.
 * - Система отчета представлена через интерфейс Report.
 * - Программисты реализовали систему отчетов в классе ReportEngine.
 *
 * В компании есть три департамента: бухгалтерия, программисты, HR.
 * Всем департаментам нужны отчеты.
 *   (Старая отчетность будет отображаться через class ReportOld)
 * - Через месяц применения системы отчетности отдел программистов потребовал ответы в виде html.
 *   (Для реализации этого требования создадаим class ReportDeveloper)
 * - Отдел бухгалтерии попросил изменить вид зарплаты.
 *   (Для реализации этого требования создадаим class ReportBookkeeping)
 * - Отдел HR попросил выводить сотрудников в порядке убывания зарплаты
 *   и убрать поля даты найма и увольнения.
 *   (Для реализации этого требования создадаим class ReportHR)
 *
 * Задание.
 * 1. Доработайте систему ответов и измените отчетность,
 *    чтобы она удовлетворяла требованиям всех групп.
 * 2. Первоначально создайте тесты на требования групп.
 */
public class Employee {
    private String name;
    private Calendar hired;
    private Calendar fired;
    private double salary;

    public Employee(String name, Calendar hired, Calendar fired, double salary) {
        this.name = name;
        this.hired = hired;
        this.fired = fired;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getHired() {
        return hired;
    }

    public void setHired(Calendar hired) {
        this.hired = hired;
    }

    public Calendar getFired() {
        return fired;
    }

    public void setFired(Calendar fired) {
        this.fired = fired;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Employee employee = (Employee) o;
        return Objects.equals(name, employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
