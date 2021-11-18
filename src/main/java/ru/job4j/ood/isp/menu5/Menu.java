package ru.job4j.ood.isp.menu5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Menu implements Output, Action, Add {

    public List<Task> taskList = new ArrayList<>();

    @Override
    public void add(Task task) {
        if (task.getParent() == null) {
            taskList.add(task);
        } else {
            add(task, task.getParent(), this.taskList);
        }
    }

    public void add(Task task, String parent, List<Task> taskList) {
        for (Task simpleTask : taskList) {
            if (simpleTask.getName().equals(parent)) {
                simpleTask.getChildrenList().add(task);
            } else if (simpleTask.getChildrenList().size() > 0) {
                add(task, parent, simpleTask.getChildrenList());
            }
        }
    }

    @Override
    public void print() {
        for (Task task : taskList) {
            String[] strings = task.name.split(" ");
            StringBuilder str = new StringBuilder();
            for (int i = 0; i < strings[1].length(); i++) {
                str.append("-");
            }
            System.out.println(str + task.getName());
            if (task.getChildrenList().size() > 0) {
                print(task);
            }
        }
    }

    public void print(Task task) {
        for (Task tasks : task.getChildrenList()) {
            String[] strings = task.name.split(" ");
            StringBuilder str = new StringBuilder();
            for (int i = 0; i < strings[1].length() + 2; i++) {
                str.append("-");
            }
            System.out.println(str + tasks.getName());
            if (tasks.getChildrenList().size() > 0) {
                print(tasks);
            }
        }
    }

    @Override
    public void action() throws IOException {
        boolean run = true;
        while (run) {
            System.out.println("Введите номер задания ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String name = reader.readLine();
            Task task = findOfTask("Задача " + name, taskList);
            System.out.println(task.getName());
            run = false;
        }
    }

    public Task findOfTask(String name, List<Task> list) {
        List<Task> taskArrayList = new ArrayList<>();
        for (Task simpleTask : list) {
            if (name.equals(simpleTask.getName())) {
                return simpleTask;
            } else if (simpleTask.getChildrenList().size() > 0) {
                taskArrayList.addAll(simpleTask.getChildrenList());
            }
        }
        if (taskArrayList.size() > 0) {
            return findOfTask(name, taskArrayList);
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        Menu menu = new Menu();
        menu.add(new Task("Задача 1.1."));
        menu.add(new Task("Задача 1.1.1.", "Задача 1.1."));
        menu.add(new Task("Задача 1.1.2.", "Задача 1.1."));
        menu.add(new Task("Задача 1.1.2.1", "Задача 1.1.2."));
        menu.add(new Task("Задача 1.2."));
        menu.add(new Task("Задача 1.2.1.", "Задача 1.2."));
        menu.add(new Task("Задача 1.2.2.", "Задача 1.2."));
        menu.print();
        menu.action();
    }
}
