package ru.job4j.ood.isp.menu5;

import java.util.ArrayList;
import java.util.List;

public class Task {

    public String name;
    List<Task> childrenList = new ArrayList<>();
    public String parent;

    public Task(String name, String parent) {
        this.name = name;
        this.parent = parent;
    }

    public Task(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Task> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(List<Task> childrenList) {
        this.childrenList = childrenList;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }
}
