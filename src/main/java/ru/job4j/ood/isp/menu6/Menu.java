package ru.job4j.ood.isp.menu6;

public interface Menu<K extends Comparable<K>> extends Printable, CRUD<K, Action> {
}
