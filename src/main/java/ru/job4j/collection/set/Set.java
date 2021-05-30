package ru.job4j.collection.set;

public interface Set<T> extends Iterable {
    boolean add(T value);
    boolean contains(T value);
}
