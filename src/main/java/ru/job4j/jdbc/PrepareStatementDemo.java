package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

/**
 * PrepareStatement.
 * Класс PrepareStatement описывает вставку,обновления,
 * удаления и получения всех элементов в таблице.
 * Задание:
 * 1. С помощью класса PrepareStatement вставить аргументы в запрос,
 *    используя специальные методы.
 * 2. Получить id вставленного элемента.
 * --------
 * Рассмотрим, как передаются аргументы:
 *      1. В БД idea_db создадим таблицу cities(id, name, population).
 *         create table cities(
 *             id serial primary key,
 *             name text,
 *             population int
 *          );
 *      2. Создадим модель City (class City).
 *      3. Напишем код вставки данных в таблицу.
 *      4. Напишем код обновления данных.
 *      5. Напишем код удаления данных из таблицы.
 *      6. Напишем код получения всех элементов таблицы.
 *        - На чистом SQL.
 *        - С использованием JDBC.
 */

public class PrepareStatementDemo {
    private Connection connection;
    public PrepareStatementDemo() throws Exception {
        initConnection();
    }

    /**
     * 3.  Пишем код вставки данных в таблицу.
     * ----------
     * Производим подключение к БД idea_db
     * регистрируем драйвер в системе
     * указываем путь до файла где хранятся адрес бд, логин, пароль
     */
    public void initConnection() throws Exception {
        Class.forName("org.postgresql.Driver");
        Config config = new Config("./data/app.properties");
        config.load();
        String url = config.value("hibernate.connection.url");
        String login = config.value("hibernate.connection.username");
        String password = config.value("hibernate.connection.password");
        connection = DriverManager.getConnection(url, login, password);
    }

    /**
     *   Заполняем таблицу cities.
     *   Во-первых, параметры, т.е. места куда будут подставляться аргументы обозначаются «?».
     *   Во-вторых, для подстановки аргументов используется метод “setТип(позиция, аргумент)”.
     *   В-третьих, позиция аргумента считается как его порядковый номер,
     *   а не как индекс, т.е. позиции начинаются с 1.
     */
    public void insert(City city) {
        try (PreparedStatement statement =
                connection.prepareStatement("insert into cities(name, population) values (?, ?)")) {
            statement.setString(1, city.getName());
            statement.setInt(2, city.getPopulation());
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 4. Пишем код обновления данных в таблице.
     * Во-первых, метод update() возвращает boolean, это нужно для того,
     * чтобы узнать произошло обновление или нет.
     * Во-вторых, чтобы узнать произошло само обновление мы используем метод executeUpdate(),
     * если это метод возвращает 0, то значит оно не произошло, поэтому мы проверяем, что результат больше 0.
     */
    public boolean update(City city) {
        boolean result = false;
        try (PreparedStatement statement =
                connection.prepareStatement("update cities set name = ?, population = ? where id = ?")) {
            statement.setString(1, city.getName());
            statement.setInt(2, city.getPopulation());
            statement.setInt(3, city.getId());
            result = statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 5. Пишем код удаления данных из таблицы (аналогичен коду обновления).
     */
    public boolean delete(int id) {
        boolean result = false;
        try (PreparedStatement statement =
                connection.prepareStatement("delete from cities where id = ?")) {
            statement.setInt(1, id);
            result = statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 6. Пишем код получения всех элементов из таблицы cities.
     * Во-первых, ResultSet мы использовали вместе с try-with-resources.
     * Во-вторых, чтобы получить доступ к элементу записи используем метод «getТип(имя_столбца)».
     * В-третьих, чтобы сдвинуть курсор используется метод next(),
     * если он возвращает true, то сдвиг произошел и мы можем получить данные.
     */
    public List<City> findAll() {
        List<City> cities = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("select * from cities")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    cities.add(new City(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("population")
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cities;
    }

    /**
     * Получить id вставленного элемента.
     * 1.  На чистом SQL.
     * В SQL есть ключевое слово RETURNING(поля), которое мы можем использовать в запросе.
     * В итоге запрос вставки будет выглядеть так:
     * insert into cities(name, population) values ('Ufa', 1000000) returning (id);
     * -------
     * 2. С использованием JDBC.
     * Для того чтобы получить id. Нужно при создании PrepareStatement
     * вторым аргументом передать Statement.RETURNING_GENERATED_KEYS.
     * После, как обычно выполнить запрос.
     * Наконец, чтобы получить ключ нужно вызвать метод getGeneratedKeys().
     * Перепишем метод insert, так чтобы он возвращал переданный city,
     * только уже с проставленным id из БД.

    public City insert(City city) {
        try (PreparedStatement statement =
                connection.prepareStatement("insert into cities(name, population) values (?, ?)",
                        Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, city.getName());
            statement.setInt(2, city.getPopulation());
            statement.execute();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    city.setId(generatedKeys.getInt(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return city;
    }

     */
    /**
     * Реализация вышеперечисленных методов в main
     */
    public static void main(String[] args) throws Exception {
        List<City> cities = new ArrayList<>();
        City cityPalma = new City(1, "Palma", 5000);
        City cityMalaga = new City(2, "Malaga", 6000);
        City cityBerlin = new City(3, "Berlin", 7000);
        PrepareStatementDemo preparedStatementDemo = new PrepareStatementDemo();
        preparedStatementDemo.insert(cityBerlin);
        preparedStatementDemo.insert(cityMalaga);
        preparedStatementDemo.insert(cityPalma);
        cities = preparedStatementDemo.findAll();
        System.out.println(cities.toString());
        preparedStatementDemo.delete(2);
        cities = preparedStatementDemo.findAll();
        System.out.println(cities.toString());
    }
}
