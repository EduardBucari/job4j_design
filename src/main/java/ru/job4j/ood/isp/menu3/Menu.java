package ru.job4j.ood.isp.menu3;

public interface Menu {
    void addTask(Task adding, Task toTask);
    Task findTask(String name);
    boolean deleteTask(String name, Task menu);
}
