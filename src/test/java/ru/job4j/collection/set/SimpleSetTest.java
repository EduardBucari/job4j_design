package ru.job4j.collection.set;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleSetTest {

    @Test
    public void whenAddNonNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(1));
        assertTrue(set.contains(1));
        assertFalse(set.add(1));
    }

    @Test
    public void whenAddNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(null));
        assertTrue(set.contains(null));
        assertFalse(set.add(null));
    }

    @Test
    public  void whenAddDuplicates() {
        Set<Integer> set = new SimpleSet<>();
        set.add(1);
        Iterator<Integer> i = set.iterator();
        assertThat(i.next(), is(1));
        assertThat(set.add(1), is(false));
    }
}