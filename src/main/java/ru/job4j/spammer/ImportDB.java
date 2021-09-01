package ru.job4j.spammer;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Загрузка базы спамеров.
 * Задача:
 * Для создания базы данных для спамеров необходимо:
 * Существует файл, формат данных  dump.txt
 * - Необходимо перевести данные файла в базу данных PostgreSQL.
 *
 *  Класс ImportDB описывает добавление данных в БД из файла dump.txt
 */
public class ImportDB {
    private Properties cfg;
    private String dump;

    public ImportDB(Properties cfg, String dump) {
        this.cfg = cfg;
        this.dump = dump;
    }

    /**
     * Метод load() описывает чтение из файла в лист
     * Метод users.add(new User(temp[0], temp[1]));
     * проверяет что массив соответствующей длины.
     * return users - Возвращаем заполненый лист.
     */
    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
            /* rd.lines().forEach(...); */
            rd.lines().forEach(x -> {
                String[] temp = x.split(";");
                if (temp.length < 2) {
                    throw new IllegalArgumentException("wrong arguments number");
                }
                users.add(new User(
                        temp[0],
                        temp[1]
                ));
            });
        }
        return users;
    }

    /**
     * Метод save описывает сохранение нашего заполненного листа в БД.
     *
     * Подключаемся к БД, регистрируем драйвер,
     * адрес, логин и пароль будут вызываться с точки доступа,
     * циклом for each бежим по листу и записываем данные в БД.
     */
    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(cfg.getProperty("jdbc.driver"));
        try (Connection cnt = DriverManager.getConnection(
                cfg.getProperty("jdbc.url"),
                cfg.getProperty("jdbc.username"),
                cfg.getProperty("jdbc.password")
        )) {
            for (User user : users) {
                try (PreparedStatement ps = cnt.prepareStatement(
                        /* .... */
                        "insert into users (name, email) values (?, ?);")) {
                    ps.setString(1, user.name);
                    ps.setString(2, user.email);
                    ps.execute();
                }
            }
        }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }

    public static void main(String[] args) throws Exception {
        Properties cfg = new Properties();
        try (FileInputStream in = new FileInputStream("./data/importDB.properties")) {
            cfg.load(in);
        }
        ImportDB db = new ImportDB(cfg, "./data/dump.txt");
        db.save(db.load());
    }
}