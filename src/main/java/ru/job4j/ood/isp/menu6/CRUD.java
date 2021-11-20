package ru.job4j.ood.isp.menu6;

public interface CRUD<K, V> {
    boolean add(K parent, K node, V action);
    boolean remove(K node);
    boolean update(K note, V action);
    V get(K node);
}
