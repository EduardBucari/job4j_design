package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Что такое Socket?
 * Задание.
 * Доработайте класса ru.job4j.io.EchoServer.
 * Если клиент отправлять запрос http://localhost:9000/?msg=Bye нужно завершить работу сервера.
 */
public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()))) {
                    String str;
                    while (!(str = in.readLine()).isEmpty()) {
                        if (str.contains("Bye")) {
                            out.write("HTTP/1.1 200 OK\r\n".getBytes());
                            server.close();
                        }
                    }
                }
            }
        }
    }
}


