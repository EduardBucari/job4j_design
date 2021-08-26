package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.sql.*;
import java.util.Properties;

/**
 * Statement.
 * Задание:
 * 1. Дан каркас класса TableEditor. Реализовать его методы.
 *    class TableEditor описывает работу в бд, создание/удаление таблицы,
 *    добавление/удаление/переименование стобцов.
 * 2. Описание методов:
 *    - createTable() – создает пустую таблицу без столбцов с указанным именем;
 *    - dropTable() – удаляет таблицу по указанному имени;
 *    - addColumn() – добавляет столбец в таблицу;
 *    - dropColumn() – удаляет столбец из таблицы;
 *    - renameColumn() – переименовывает столбец.
 * 3. Чтение настроек должно идти из файла *.properties
 * 4. Избавиться от дублирования кода, если таковое имеется.
 */
public class TableEditor implements AutoCloseable {
    private Connection connection;
    private Properties properties;

    public TableEditor(Properties properties) throws Exception {
        this.properties = properties;
        initConnection();
    }

    /**
     * Метод initConnection() описывает соединение с БД.
     *
     * Указываем путь к файлу с настройками
     * и берем оттуда логин, пароль, и путь к БД.
     */
    private void initConnection() throws Exception {
        Class.forName("org.postgresql.Driver"); //Регистрация драйвера в системе.
        Config config = new Config("./data/app.properties");
        config.load();
        String url = config.value("hibernate.connection.url");
        String login = config.value("hibernate.connection.username");
        String password = config.value("hibernate.connection.password");
        connection =  DriverManager.getConnection(url, login, password);
    }

    /**
     * Метод createTable(String tableName) описывает создание
     * пустой таблицы без столбцов и с указание имени таблицы.
     */
    public void createTable(String tableName) throws Exception {
        try (Statement statement = connection.createStatement()) {
            statement.execute(String.format("create table if not exists %s();", tableName));
        }
    }

    /**
     * Метод dropTable(String tableName) удаляет таблицу
     * по указанноиу имени.
     */
    public void dropTable(String tableName) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(String.format("drop table %s;", tableName));
        }
    }

    /**
     * Метод addColumn() добавляет столбец в таблицу:
     * @param tableName - имя таблицы в которую добавляем столбец.
     * @param columnName - имя столбца.
     * @param type - тип столбца.
     */
    public void addColumn(String tableName, String columnName, String type) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(String.format("alter table %s add column %s %s", tableName, columnName, type));
        }
    }

    /**
     * Метод dropColumn() удаляет столбец из таблицы:
     * @param tableName - имя таблицы из которой мы удаляем столбец.
     * @param columnName - имя столбца, который мы удаляем.
     */
    public void dropColumn(String tableName, String columnName) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(String.format("alter table %s drop column %s", tableName, columnName));
        }
    }

    /**
     * Метод renameColumn() переименовывает столбец:
     * @param tableName - имя таблицы в которой переименовывается столбец.
     * @param columnName - имя столбца который нужно переименовать.
     * @param newColumnName - новое имя столбца.
     */
    public void renameColumn(String tableName, String columnName, String newColumnName) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(String.format(
                    "alter table %s rename column %s to %s", tableName, columnName, newColumnName));
        }
    }

    /**
     * Метод getTableScheme() выводит таблицу в кансоль:
     * @param connection - на выходе параметр соединения.
     * @param tableName - имя столбца, который необходимо вывести.
     * @return - возвращаем результат.
     * @throws Exception - выбрасываем все исключения.
     */
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

    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        TableEditor tableEditor = new TableEditor(properties);
        tableEditor.dropTable("test_db_statement");
        tableEditor.createTable("test_db_statement");
        tableEditor.addColumn("test_db_statement", "name", "varchar(50)");
        tableEditor.addColumn("test_db_statement", "surname", "varchar(50)");
        tableEditor.addColumn("test_db_statement", "height", "integer");
        tableEditor.addColumn("test_db_statement", "weight", "integer");
        tableEditor.dropColumn("test_db_statement", "weight");
        tableEditor.renameColumn("test_db_statement", "height", "Reheight");
        System.out.println(TableEditor.getTableScheme(tableEditor.connection, "test_db_statement"));
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}
