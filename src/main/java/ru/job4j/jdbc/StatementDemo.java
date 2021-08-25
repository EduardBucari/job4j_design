package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.sql.*;
import java.util.StringJoiner;

/**
 * Statement.
 * Задние:
 * В кансоли при выводе на экран, создать таблицу со столбцами: NAME и TYPE.
 */
public class StatementDemo {

    // Вынесем код создания подключения в отдельный метод:
    private static Connection getConnection() throws Exception {
        Class.forName("org.postgresql.Driver");
        Config config = new Config("./data/app.properties");
        config.load();
        String url = config.value("hibernate.connection.url");
        String login = config.value("hibernate.connection.username");
        String password = config.value("hibernate.connection.password");
        return DriverManager.getConnection(url, login, password);
    }
    public static void main(String[] args) throws Exception {

        // Создадим запрос на создание таблицы:
        try (Connection connection = getConnection()) {
            try (Statement statement = connection.createStatement()) {
                String sql = String.format(
                        "create table if not exists demo_table(%s, %s);",
                        "id serial primary key",
                        "name varchar(255)"
                );

                // мы создали объект для запроса. Для его выполнения существует метод: execute(),
                statement.execute(sql);
                System.out.println(getTableScheme(connection, "demo_table"));
            }
        }
    }

    // Чтобы проверить, что таблица создалась давайте выведем ее схему, а именно ее столбцы и их типы.
    // Для этого напишем отдельный метод:
    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        StringBuilder sb = new StringBuilder();
        DatabaseMetaData metaData = connection.getMetaData();
        try (ResultSet columns = metaData.getColumns(null, null, tableName, null)) {
            sb.append(String.format("%-15s %-15s%n", "column", "type"));
            while (columns.next()) {
                sb.append(String.format("%-15s %-15s%n",
                        columns.getString("COLUMN_NAME"),
                        columns.getString("TYPE_NAME")));
            }
        }
        return sb.toString();
    }

}
