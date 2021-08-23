package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * JDBC
 * Задание:
 * Доработайте код программы чтобы чтение url, имени пользователя и пароля происходило из файла app.properties.
 * Для чтения используйте класс Config, который Вы писали, когда проходили блок по IO.
 *
 * указать путь до файла Config config = new Config("./data/app.properties");
 * url из файла properties String url = config.value("hibernate.connection.url");
 * login из файла properties String login = config.value("hibernate.connection.username");
 * пароль из файла properties String password = config.value("hibernate.connection.password");
 */
public class ConnectionDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        // Регистрация драйвера в системе.
        Class.forName("org.postgresql.Driver");
        Config config = new Config("./data/app.properties");
        config.load();
        String url = config.value("hibernate.connection.url");
        String login = config.value("hibernate.connection.username");
        String password = config.value("hibernate.connection.password");

        // Подключение к postgres через jdbc
        try (Connection connection = DriverManager.getConnection(url, login, password)) {

            // Используем класс DatabaseMetaData для получинея имени пользователя и url
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }
    }
}
