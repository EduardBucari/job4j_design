package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.io.UsageLog4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * Что такое Socket?
 * Задание.
 * Доработайте класса ru.job4j.io.EchoServer.
 * Если клиент отправлять запрос http://localhost:9000/?msg=Bye нужно завершить работу сервера.
 *
 * Бот (работающий по протоколу HTTP)
 * Сервер, представляет собой, простого бота, который реагирует на
 * команды клиента передаваемые в виде Get запроса.
 * Задание 2.
 * В этом задании Вам нужно доработать класс ru.job4j.io.EchoServer.
 * Клиент отправляет запросы, сервер должен ему отвечать.
 * 1. Ответить - (msg=Hello)         (http://localhost:9000/?msg=Hello)
 * 2. Завершить работу сервера - (msg=Exit) (http://localhost:9000/?msg=Exit)
 * 3. Во всех остальных случаях отправлять текст запроса - (msg=Any)
 *    Запрос с параметром What, должен вернуть ответ типа What (http://localhost:9000/?msg=What)
 *
 * Slf4j - вывод exception.
 * Задание 3.
 * В задании Сокет/Бот уберите из сигнатуры метода main исключение.
 * Обработайте его через catch c выводом в логгер.
 */

public class EchoServer {
    public static void main(String[] args) {
        final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                socket.setSoTimeout(3000);
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String str;
                    String answer = "server.";
                    str = in.readLine();
                    while (!str.isEmpty()) {
                        System.out.println(str);
                        if (str.contains("/?msg")) {
                            String[] message = str.split(" ");
                            String[] splitMsg = message[1].split("msg=", 2);
                            String request = splitMsg[1];
                            if (request.equals("Hello")) {
                                answer = "HELLO !";
                            } else if (request.equals("EXIT")) {
                                out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                                out.write("Server stopped...".getBytes());
                                server.close();
                                break; /* Сервер закрыт. */
                            } else {
                                answer = request;
                            }
                        }
                        str = in.readLine();
                    }
                    if (!server.isClosed()) {
                        out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                        out.write(answer.getBytes());
                    }
                }
            }
        } catch (IOException e) {
            LOG.error("Socket error: ", e);
        }
    }
}


