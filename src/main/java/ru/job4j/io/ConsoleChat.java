package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *  Кодировка.
 *  Задание.
 * В этом задании необходимо создать программу 'Консольный чат'. Некоторое описание:
 * - пользователь вводит слово-фразу, программа берет случайную фразу из текстового файла и выводит в ответ.
 * - программа замолкает если пользователь вводит слово «стоп», при этом он может продолжать отправлять сообщения в чат.
 * - если пользователь вводит слово «продолжить», программа снова начинает отвечать.
 * - при вводе слова «закончить» программа прекращает работу.
 * - запись диалога, включая слова-команды стоп/продолжить/закончить должны быть записаны в текстовый лог.
 *
 * Класс принимает в конструктор 2 параметра - имя файла,
 * в который будет записан весь диалог между ботом и пользователем,
 * и имя файла в котором находятся строки с ответами, которые будет использовать бот.
 * Вам нужно реализовать метод run(), логику получения ответа от бота из
 * файла допускается вынести в отдельный метод.
 */
public class ConsoleChat {
    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> baseAnswerBot = new ArrayList<>();
        List<String> logChat = new ArrayList<>();
        readList(baseAnswerBot, botAnswers);
        Scanner scan = new Scanner(System.in);
        String buffStr;
        boolean turnOnBot = true;
        while (scan.hasNextLine()) {
            buffStr = scan.nextLine();
            logChat.add(buffStr);
            if (buffStr.equals(OUT)) {
                scan.close();
                break;
            }
            if (buffStr.equals(STOP)) {
                turnOnBot = false;
            }
            if (buffStr.equals(CONTINUE) && !turnOnBot) {
                turnOnBot = true;
            }
            if (turnOnBot) {
                buffStr = botSays(baseAnswerBot);
                logChat.add(buffStr);
                System.out.println(buffStr);
            }
        }
        writeLog(logChat, path);
    }

    private void writeLog(List<String> list, String path) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, StandardCharsets.UTF_8, true))) {
            for (String s : list) {
                pw.write(s);
                pw.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readList(List<String> list, String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            br.lines().forEach(list :: add);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String botSays(List<String> data) {
        return data.get((int) (Math.random() * data.size()));
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat(
                "chatWithBot.log",
                "answerBot.txt");
        cc.run();
    }
}
