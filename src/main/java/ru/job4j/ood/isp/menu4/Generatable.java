package ru.job4j.ood.isp.menu4;

import java.util.List;

public interface Generatable<T> {
    void generate(List<T> list);
}
