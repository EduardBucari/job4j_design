package ru.job4j.collection;

import org.junit.Test;
import ru.job4j.collection.Analysis.Info;
import ru.job4j.collection.Analysis.User;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AnalysisTest {

    @Test
    public void whenDeleted() {
        List<User> previous = List.of(
                new User(1, "a"),
                new User(2, "b")
        );
        List<User> current = List.of(new User(1, "a"));
        Info res = new Analysis().diff(previous, current);
        assertThat(res.deleted, is(1));
    }

    @Test
    public void whenChangesAndAddition() {
        List<User> previous = List.of(
                new User(1, "a"),
                new User(2, "b")
        );
        List<User> current = List.of(
                new User(1, "a"),
                new User(2, "c"),
                new User(3, "d")
        );
        Info res = new Analysis().diff(previous, current);
        assertThat(res.changed, is(1));
        assertThat(res.added, is(1));
    }

    @Test
    public void whenAddition() {
        List<User> previous = List.of(new User(1, "a"));
        List<User> current = List.of(new User(1, "c"),
                new User(2, "b")
        );
        Info res = new Analysis().diff(previous, current);
        assertThat(res.added, is(1));
    }
}