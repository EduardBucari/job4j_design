package ru.job4j.tdd;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Всю систему мы описали через интерфейсы.
 * Опишем поведение пользователя через тесты.
 * Чтобы описать тесты, потребуется создать пустые классы
 * реализующие нужные интерфейсы:
 * Account account = new AccountCinema();
 */
public class CinemaTest {

    @Test
    public void buy() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket, is(ticket));
    }

    @Test
    public void find() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> true);
        assertThat(sessions, is(sessions));
    }

    @Test
    public void add() {
       Session3D session3D = new Session3D();
       List<Session> rsl = new ArrayList<>();
       rsl.add(session3D);
       Cinema3D cinema3D = new Cinema3D();
       cinema3D.add(session3D);
       assertThat(rsl.size(), is(1));
    }

    @Test
    public void buyTwo() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Ticket ticket1 = new Ticket3D();
        List<Ticket> list = new ArrayList<>(List.of(new Ticket3D(), new Ticket3D()));
        Ticket ticket = cinema.buy(account, 1, 1, date);
        list.remove(0);
        assertThat(list.size(), is(1));
    }

    @Test
    public void whenTicket() {
        List<Ticket> list = new ArrayList<>(List.of(new Ticket3D()));
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        list.remove(0);
        assertFalse(list.size() == 1);
    }
}