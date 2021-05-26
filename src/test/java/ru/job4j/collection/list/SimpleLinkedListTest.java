package ru.job4j.collection.list;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.Iterator;
import java.util.ConcurrentModificationException;

import static org.junit.Assert.*;

public class SimpleLinkedListTest {

    @Test
    public void whenAddAndGet() {
        List<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        assertThat(list.get(0), Is.is(1));
        assertThat(list.get(1), Is.is(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetFromOutOfBoundsExceptionThrow() {
        List<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        list.get(2);
    }

    @Test
    public void whenGetIteratorTwiceThenEveryFromBegin() {
        List<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);

        Iterator<Integer> first = list.iterator();
        assertThat(first.hasNext(), Is.is(true));
        assertThat(first.next(), Is.is(1));
        assertThat(first.hasNext(), Is.is(true));
        assertThat(first.next(), Is.is(2));
        assertThat(first.hasNext(), Is.is(false));

        Iterator<Integer> second = list.iterator();
        assertThat(second.hasNext(), Is.is(true));
        assertThat(second.next(), Is.is(1));
        assertThat(second.hasNext(), Is.is(true));
        assertThat(second.next(), Is.is(2));
        assertThat(second.hasNext(), Is.is(false));
    }

    @Test
    public void whenAddIntoEmptyList() {
        List<Integer> list = new SimpleLinkedList<>();
        list.add(10);
        list.add(20);
        Integer expected = list.get(1);
        assertThat(expected, Is.is(20));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenFailFastException() {
        List<Integer> list = new SimpleLinkedList<>();
        list.add(10);
        Iterator<Integer> iterator = list.iterator();
        list.add(10);
        iterator.next();
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetOutBound() {
        List<Integer> list = new SimpleLinkedList<>();
        list.add(10);
        assertThat(list.get(1), Is.is(0));
    }
}