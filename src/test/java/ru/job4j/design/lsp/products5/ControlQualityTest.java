package ru.job4j.design.lsp.products5;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Test "Хранилище продуктов"
 *
 * @author Bucari Eduard
 * @version 0.5
 * @since 24.11.2021
 */
public class ControlQualityTest {
    /**
     * Ссылка на список хранилищ.
     */
    List<Storage> storageList;
    /**
     * Ссылка на объект контроля качества.
     */
    ControlQuality controlQuality;

    @Before
    public void setup() {
        storageList = List.of(
                new Shop(),
                new Trash(),
                new Warehouse()
        );
    }

    /**
     * Свежесть продукта израсходована на 103% = Trash.
     * Warehouse.foodList.size=0;
     * Shop.foodList.size=0;
     */
    @Test
    public void whenProductGoToTrash() {
        Food coffee = new Coffee("Jacobs", LocalDate.now().minusMonths(1),
                LocalDate.now().minusDays(1), 120, 0);
        controlQuality = new ControlQuality(storageList);
        controlQuality.distribute(coffee);
        List<Food> expected = storageList.get(1).get();
        assertThat(expected.get(0).getName(), is("Jacobs"));
        expected = storageList.get(0).get();
        assertThat(expected.size(), is(0));
        expected = storageList.get(2).get();
        assertThat(expected.size(), is(0));
    }

    /**
     * Свежесть продукта израсходована на 96% = Shop.
     * Trash.foodList.size=0;
     * Warehouse.foodList.size=0;
     */
    @Test
    public void whenProductGoToShopWithDiscount() {
        Food coffee = new Coffee("MacCoffee", LocalDate.now().minusMonths(1),
                LocalDate.now().plusDays(1), 100, 0);
        controlQuality = new ControlQuality(storageList);
        controlQuality.distribute(coffee);
        List<Food> expected = storageList.get(0).get();
        assertThat(expected.get(0).getDiscount(), is(25));
        expected = storageList.get(1).get();
        assertThat(expected.size(), is(0));
        expected = storageList.get(2).get();
        assertThat(expected.size(), is(0));
    }

    /**
     * Свежесть продукта израсходована на 3% = Warehouse.
     * Shop.foodList.size=0;
     * Trash.foodList.size=0;
     */
    @Test
    public void whenProductGoToWarehouse() {
        Food coffee = new Coffee("BestOfTheBest", LocalDate.now().minusDays(1),
                LocalDate.now().plusMonths(1), 300, 0);
        controlQuality = new ControlQuality(storageList);
        controlQuality.distribute(coffee);
        List<Food> expected = storageList.get(2).get();
        assertThat(expected.get(0).getName(), is("BestOfTheBest"));
        expected = storageList.get(0).get();
        assertThat(expected.size(), is(0));
        expected = storageList.get(1).get();
        assertThat(expected.size(), is(0));
    }

    /**
     * Create date over expiry date.
     */
    @Test(expected = IllegalArgumentException.class)
    public void whenCreateDataOverExpiryDate() {
        Food coffee = new Coffee("IceCoffee", LocalDate.now().plusDays(1),
                LocalDate.now(), 50, 0);
        controlQuality = new ControlQuality(storageList);
        controlQuality.distribute(coffee);
    }

    /**
     * Create date equals expiry date.
     */
    @Test(expected = IllegalArgumentException.class)
    public void whenCreateDateEqualsExpiryDate() {
        Food coffee = new Coffee("IceCoffee", LocalDate.now(),
                LocalDate.now(), 50, 0);
        controlQuality = new ControlQuality(storageList);
        controlQuality.distribute(coffee);
    }
}