package ru.job4j.ood.isp.menu2;

import java.util.*;

public class CreateMenu implements Generator, Printer {

    public List<Item> generateMenu(List<Item> items) {
        int i = 0;
        List<Item> resultList = new ArrayList<>();
        Item root = items.get(0);
        for (Item item : items) {

            if (item.getName().contains(root.getName())
                && item.getName().length() - root.getName().length() == 2) {
                root.add(item);
            } else if (item.getName().contains(root.getName())
                && item.getName().length() - root.getName().length() > 2) {
                root = root.getChildren().get(i);
                root.add(item);
            } else if (item.getName().length() == root.getName().length() && !item.getName().equals(root.getName())) {
                root = getItem(item, items);

                root.add(item);
                i++;
            } else {
                root = item;
                resultList.add(root);
            }
        }
        return resultList;
    }

    private Item getItem(Item item, List<Item> items) {
        int size = 0;
        for (Item rsl : items) {
            if (rsl.getName().length() == item.getName().length()) {
                return items.get(size - 1);
            }
            size++;
        }
        throw new NullPointerException("Значение - null");
    }


    public void printMenu(List<Item> items) {
        String prob = " ";
        int i = 0;
        for (Item item : items) {
            System.out.println(item.getName());
            Item root = item;
            List<Item> rootList = root.getChildren();
            while (!rootList.isEmpty()) {
                root = rootList.get(0);
                System.out.println(prob + root.getName());
                rootList = root.getChildren();
                if (!rootList.isEmpty()) {
                    prob = prob + prob;
                }
            }
            prob = " ";
        }
    }

    public static void main(String[] args) {
        List<Item> items = List.of(new Item("Поле 1."),
                new Item("Поле 1.1."),
                new Item("Поле 1.1.1."),
                new Item("Поле 1.1.1.1."),
                new Item("Поле 1.1.2."),
                new Item("Поле 1.1.2.1"),
                new Item("Поле 2."),
                new Item("Поле 2.1.")
                );
        CreateMenu outMenu = new CreateMenu();
        outMenu.generateMenu(items);
        List<Item> rsl = outMenu.generateMenu(items);
        outMenu.printMenu(rsl);
    }
}
