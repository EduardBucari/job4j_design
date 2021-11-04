package ru.job4j.ood.isp;

/**
 * Принцип разделения интерфейсов.
 * принцип ISP (Interface Segregation Principle) - принцип разделения интерфейсов.
 * Суть этого принципа: "Программные сущности не должны зависеть от методов, которые они не используют."
 *
 * Задание:
 * Придумайте 3 примера, когда происходит нарушение принципа ISP.
 *
 * Пример 1.
 * Нарушение ISP - не на всех заправках есть мойка и магазин продуктов.
 */
interface PetrolStation {
    void refuel();
    void carWashing();
    void buyProducts();
}

/**
 * Пример 2.
 * Нарушение ISP - не во всех магазинах есть отделы с бытовой химией и медикаментами.
 */
interface Shop {
    void foods();
    void chemicals();
    void medicines();
}

/**
 * Пример 3.
 * Нарушение ISP - не всегда для работы с БД требуется чтение/запись файлов.
 */
interface DataBase {
    void readFromDataBase();
    void writeToDataBase();
    void readFromFile();
    void writeToFile();

}
