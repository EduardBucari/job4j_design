package ru.job4j.collection.map;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SimpleMapTest {
    SimpleMap<String, Integer> simpleMap = new SimpleMap<>();

    @Test
    public void whenInsert() {
        assertThat(simpleMap.insert("one", 1), is(true));
        assertThat(simpleMap.insert("two", 2), is(true));
        assertThat(simpleMap.insert("two", 2), is(false));
    }

    @Test
    public void whenGet() {
        simpleMap.insert("one", 1);
        assertThat(simpleMap.get("one"), is(1));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenFailFastException() {
        simpleMap.insert("one", 1);
        Iterator<Integer> iterator = simpleMap.iterator();
        assertThat(iterator.next(), is(1));
        simpleMap.insert("two", 2);
        assertThat(iterator.next(), is(2));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenHasNextException() {
        simpleMap.insert("one", 1);
        Iterator<Integer> iterator = simpleMap.iterator();
        assertThat(iterator.next(), is(1));
        assertThat(iterator.next(), is(2));
    }

    @Test
    public void whenDeleteReturnFalse() {
        simpleMap.insert("one", 1);
        assertThat(simpleMap.delete("one"), is(true));
        assertThat(simpleMap.delete("two"), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenKeyIsAbsent() {
        simpleMap.insert("one", 1);
        assertThat(simpleMap.get("one"), is(1));
        assertThat(simpleMap.get("two"), is(2));
    }

    @Test
    public void whenExpandTable() {
        SimpleMap<Integer, Integer> input = new SimpleMap<>();
        for (int i = 0; i < 13; i++) {   /* capacity * 0.75 */
            input.insert(i, i);
        }
        assertThat(input.size(), is(37));
    }
}