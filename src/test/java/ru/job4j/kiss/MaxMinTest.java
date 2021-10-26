package ru.job4j.kiss;

import org.junit.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.*;

public class MaxMinTest {

    @Test
    public void maxInt() {
        MaxMin maxMin = new MaxMin();
        List<Integer> list = List.of(2, 7, 6, 9, 4, 8);
        int rsl = maxMin.max(list, Comparator.naturalOrder());
        assertEquals(9, rsl);
    }

    @Test
    public void minInt() {
        MaxMin maxMin = new MaxMin();
        List<Integer> list = List.of(2, 7, 6, 9, 4, 8);
        int rsl = maxMin.min(list, Comparator.naturalOrder());
        assertEquals(2, rsl);
    }

    @Test
    public void maxString() {
        MaxMin maxMin = new MaxMin();
        List<String> list = List.of("aa", "bb", "cc", "rr");
        String rsl = maxMin.max(list, Comparator.naturalOrder());
        assertEquals("rr", rsl);
    }

    @Test
    public void minString() {
        MaxMin maxMin = new MaxMin();
        List<String> list = List.of("aa", "bb", "cc", "rr");
        String rsl = maxMin.min(list, Comparator.naturalOrder());
        assertEquals("aa", rsl);
    }
}