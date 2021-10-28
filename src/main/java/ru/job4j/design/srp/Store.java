package ru.job4j.design.srp;

import java.util.List;
import java.util.function.Predicate;

/**
 * Доступ к базе данных с сотрудниками компании
 * осуществляется через интерфейс Store.
 */
public interface Store {
    List<Employee> findBy(Predicate<Employee> filter);
}
