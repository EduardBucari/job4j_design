package ru.job4j.ood.isp.menu2;


import org.junit.Test;
import org.junit.Assert;

import java.util.List;

import static org.hamcrest.Matchers.is;

public class CreateMenuTest {

    @Test
    public void whenGenerateMenu() {
        List<Item> items = List.of(new Item("Поле 1."),
                new Item("Поле 1.1."),
                new Item("Поле 1.1.1."),
                new Item("Поле 1.1.1.1."),
                new Item("Поле 1.1.2."),
                new Item("Поле 1.1.2.1."),
                new Item("Поле 2."),
                new Item("Поле 2.1.")
                );

        Item item = new Item("Поле 1.");
        Item item1 = new Item("Поле 1.1.");
        Item item2 = new Item("Поле 1.1.1.");
        Item item3 = new Item("Поле 1.1.1.1.");
        Item item4 = new Item("Поле 1.1.2.");
        Item item5 = new Item("Поле 1.1.2.1.");
        Item item6 = new Item("Поле 2.");
        Item item7 = new Item("Поле 2.1.");

        item2.add(item3);
        item4.add(item5);
        item1.add(item2);
        item1.add(item4);

        item6.add(item7);
        item.add(item1);

        List<Item> rsl = List.of(item, item6);

        CreateMenu createMenu = new CreateMenu();

        Assert.assertThat(createMenu.generateMenu(items), is((rsl)));
    }

    @Test
    public void whenGenerateMenuTWo() {
        List<Item> items = List.of(new Item("Поле 1."),
                new Item("Поле 1.1."),
                new Item("Поле 1.1.1."),
                new Item("Поле 1.1.2."),
                new Item("Поле 1.1.3."),
                new Item("Поле 2."),
                new Item("Поле 2.1."),
                new Item("Поле 2.1.2.")
        );

        Item item = new Item("Поле 1.");
        Item item1 = new Item("Поле 1.1.");
        Item item2 = new Item("Поле 1.1.1.");
        Item item3 = new Item("Поле 1.1.2.");
        Item item4 = new Item("Поле 1.1.3.");
        Item item5 = new Item("Поле 2.");
        Item item6 = new Item("Поле 2.1.");
        Item item7 = new Item("Поле 2.1.2.");

        item1.add(item2);
        item1.add(item3);
        item1.add(item4);

        item6.add(item7);

        item.add(item1);
        item5.add(item6);

        List<Item> rsl = List.of(item, item5);

        CreateMenu createMenu = new CreateMenu();

        Assert.assertThat(createMenu.generateMenu(items), is((rsl)));
    }
}