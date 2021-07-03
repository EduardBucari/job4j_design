package ru.job4j.collection;

import org.junit.Assert;
import org.junit.Test;
import ru.job4j.collection.Analyze.Info;
import ru.job4j.collection.Analyze.User;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;

public class AnalyzeTest {

    @Test
    public void whenAddTwo() {
        final List<User> previous = List.of(
                new User(1, "J"),
                new User(2, "A"),
                new User(3, "O"));
        final List<User> current = List.of(
                new User(1, "J"),
                new User(2, "A"),
                new User(3, "O"),
                new User(7, "E"),
                new User(4, "S"));
        final Info info = new Analyze().diff(previous, current);
        Assert.assertThat(info, is(new Info(2, 0, 0)));
    }

    @Test
    public void whenChangeAll() {
        final List<User> previous = List.of(
                new User(1, "J"),
                new User(2, "A"),
                new User(3, "O"));
        final List<User> current = List.of(
                new User(1, "R"),
                new User(2, "E"),
                new User(3, "S"));
        final Info info = new Analyze().diff(previous, current);
        Assert.assertThat(info, is(new Info(0, 3, 0)));
    }

    @Test
    public void whenDelAllAndAddTwo() {
        final List<User> previous = List.of(
                new User(1, "J"),
                new User(2, "A"),
                new User(4, "S"));
        final List<User> current = List.of(
                new User(5, "R"),
                new User(7, "L"));
        final Info info = new Analyze().diff(previous, current);
        Assert.assertThat(info, is(new Info(2, 0, 3)));
    }
}