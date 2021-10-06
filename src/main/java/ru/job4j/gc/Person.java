package ru.job4j.gc;

/**
 * Понятие сборки мусора.
 * Задание:
 * Продемонстрировать применение Метода finalize(), System.gc() и ключей запуска.
 * В классе Object есть метод finalize(). Этот метод вызывается перед тем как объект уничтожется,
 * мы можем применить его для демонстрации работы сборки мусора.
 *
 * 1. Создадим модель данных, класс Person с двумя полями и переопределенным метод finalize()
 * 2. Размер памяти (хипа) для нашей программы можно задать с помощью ключей -XmxNm -XmsNm
 *    соответственно максимальный и начальный размеры хипа.
 *    Зададим для нашей программы размер хипа 4 мегабайта через “Edit configuration” и
 *    далее в раздел «VM options» прописываем наши ключи.
 * 3. Создаем дополнительный class GCDemo, который создает объекты без ссылок,
 *    т.е. потенциально те, что нужно удалить.
 */
public class Person {

    private int age;
    private String name;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.printf("Removed %d %s%n", age, name);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

