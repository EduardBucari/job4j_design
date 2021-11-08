package ru.job4j.ood.isp.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * Создать меню.
 * 1. Реализовать структуру для поддержания меню.
 * 2. Каждый элемент меню имеет имя. Все меню должно выводиться на экран.
 * 3. Каждый пункт меню может быть как одиночным элементом, так и иметь дочерние подпункты.
 * 4. Все меню должно выводиться на экран. В виде дерева.
 * 5. Предусмотреть возможность определять действие, когда пользователь выбирает конкретный пункт меню.
 *
 * Например:
 * Задача 1.
 * -- Задача 1.1.
 * ----- Задача 1.1.1.
 * ----- Задача 1.1.2.
 * -- Задача 1.2.
 */
public interface Menu {
    void add(String parentName, Item child);

    class Item {
        private final String name;
        Action action;
        List<Item> itemList = new ArrayList<>();

        public Item(String name, Action action) {
            this.name = name;
            this.action = action;
        }

        public String getName() {
            return name;
        }

        public Action getAction() {
            return action;
        }

        public List<Item> getItemList() {
            return itemList;
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
            return Objects.equals(name, item.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }

        public void addItem(Item child) {
            itemList.add(child);
        }
    }
}
