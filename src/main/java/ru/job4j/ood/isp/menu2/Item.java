package ru.job4j.ood.isp.menu2;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Item {

    private String name;
    private List<Item> children = new ArrayList<>();


    public Item(String name) {
        this.name = name;
        this.children = new ArrayList<>();
    }

    public void add(Item item) {
        children.add(item);
    }

    public List<Item> getChildren() {
        return children;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return Objects.equals(name, item.name) && Objects.equals(children, item.children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children);
    }

    @Override
    public String toString() {
        return "Item{"
                + "name='" + name + '\''
                + ", children=" + children
                + '}';
    }
}