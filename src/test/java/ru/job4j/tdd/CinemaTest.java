package ru.job4j.tdd;

import org.junit.Test;
import org.junit.Ignore;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNull;

/*
 * Всю систему мы описали через интерфейсы.
 * Опишем поведение пользователя через тесты.
 * Чтобы описать тесты, потребуется создать пустые классы
 * реализующие нужные интерфейсы:
 * Account account = new AccountCinema();
 */
@Ignore
public class CinemaTest {

    /*
     * Покупка билета.
     */
    @Test
    public void buy() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket, is(new Ticket3D()));
    }

    /*
     * Поиск сеанса.
     */
    @Test
    public void find() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> true);
        assertThat(sessions, is(Arrays.asList(new Session3D())));
    }


    /*
     * У зрителя уже есть такой билет.
     */
    @Test
    public void whenHaveTicket() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Ticket ticket1 = cinema.buy(account, 1, 1, date);
        Ticket ticket2 = cinema.buy(account, 1, 1, date);
        assertNull(ticket2);
    }

    /*
     * Билетов нет на желаемое число.
     */
    @Test
    public void whenNotTicketCurrentDate() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertNull(ticket);
    }

    /*
     * Билетов нет на желаемое место.
     */
    @Test
    public void whenNotTickedCurrentPlace() {
        Account account = new AccountCinema();
        Account account2 = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        Ticket ticket2 = cinema.buy(account2, 1, 1, date);
        assertNull(ticket2);
    }

    /*
     * Нет желаемого сеанса.
     */
    @Test
    public void whenNotSession() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> false);
        assertThat(sessions.size(), is(0));
    }

    /*
     * Указана не правильная дата желаемого сеанса.
     */
    @Test(expected = IllegalArgumentException.class)
    public void whenWrongDate() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 13, 32, 25, 61);
        Ticket ticket = cinema.buy(account, 1, 1, date);
    }

    /*
     * Указано не правильное место желаемого сеанса.
     */
    @Test(expected = IllegalArgumentException.class)
    public void whenWrongPlace() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 1, 1, 21, 0);
        Ticket ticket = cinema.buy(account, 0, 0, date);
    }
}