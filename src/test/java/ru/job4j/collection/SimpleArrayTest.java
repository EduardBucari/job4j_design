package ru.job4j.collection;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArrayTest {

    @Test
    public void whenAddThenGet() {
        SimpleArray<String> array = new SimpleArray<>();
        array.add("first");
        String rsl = array.get(0);
        assertThat(rsl, is("first"));
    }

    @Test
    public void whenAddThenIt() {
        SimpleArray<String> array = new SimpleArray<>();
        array.add("first");
        String rsl = array.iterator().next();
        assertThat(rsl, is("first"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetEmpty() {
        SimpleArray<String> array = new SimpleArray<>();
        array.get(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetOutBound() {
        SimpleArray<String> array = new SimpleArray<>();
        array.add("first");
        array.get(1);
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetEmptyFromIt() {
        SimpleArray<String> array = new SimpleArray<>();
        array.iterator().next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCorruptedIt() {
        SimpleArray<String> array = new SimpleArray<>();
        array.add("first");
        Iterator<String> it = array.iterator();
        array.add("second");
        it.next();
    }

    /* Дополнительные тесты, согласно заданию. */

    @Test(expected = ConcurrentModificationException.class)
    public void whenCorruptedAddByIndex() {
        SimpleArray<String> array = new SimpleArray<>();
        array.add("first");
        Iterator<String> it = array.iterator();
        array.add(0, "second");
        it.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCorruptedRemove() {
        SimpleArray<String> array = new SimpleArray<>();
        array.add("first");
        array.add("second");
        Iterator<String> it = array.iterator();
        array.remove(0);
        it.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCorruptedSet() {
        SimpleArray<String> array = new SimpleArray<>();
        array.add("first");
        array.add("second");
        Iterator<String> it = array.iterator();
        array.add(0, "zero");
        it.next();
    }

    @Test
    public void whenGetLast() {
        SimpleArray<String> array = new SimpleArray<>();
        array.add("ten");
        array.add("twenty");
        array.add("thirty");
        assertThat(array.getLast(), is("thirty"));
    }

    @Test
    public void whenGetFirst() {
        SimpleArray<String> array = new SimpleArray<>();
        array.add("ten");
        array.add("twenty");
        array.add("thirty");
        assertThat(array.getFirst(), is("ten"));
    }

    @Test
    public void whenListSpecificCapacity() {
        SimpleArray<Integer> array = new SimpleArray<>(3);
        array.add(10);
        array.add(15);
        array.add(20);
        assertThat(array.get(0), is(10));
        assertThat(array.get(1), is(15));
        assertThat(array.get(2), is(20));
    }

    @Test
    public void whenDynamicCapacity() {
        SimpleArray<Integer> array = new SimpleArray<>(2);
        array.add(10);
        array.add(15);
        array.add(20);
        assertThat(array.get(0), is(10));
        assertThat(array.get(1), is(15));
        assertThat(array.get(2), is(20));
    }

    @Test
    public void whenClearSizeContains() {
        SimpleArray<String> array = new SimpleArray<>();
        array.add("ten");
        array.add("twenty");
        array.add("thirty");
        assertThat(array.contains("twenty"), is(true));
        array.clear();
        assertThat(array.size(), is(0));
    }

    @Test
    public void whenRemoveElement() {
        SimpleArray<String> array = new SimpleArray<>();
        array.add("first");
        array.add("second");
        assertThat(array.remove(0), is("first"));
    }
}