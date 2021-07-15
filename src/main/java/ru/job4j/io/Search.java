package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import java.util.function.Predicate;

/**
 * Сканирование файловой системы.
 * Разрабатываются программы, которые выводят содержимое всей директории включая вложенные директории.
 * Здесь используется класс Path, а не File. Path - это усовершенствованная модель File.
 * Задание.
 * 1. Разработайте программу Search, которая будет искать файлы только по определенному предикату.
 * 2. Программа должна вернуть файлы с расширением js.
 *
 * (Для выполнения задания необходимо создать специальный класс SearchFiles,
 * c логикой поиска файлов по определённому предикату.)
 *
 * Валидация параметров запуска.
 * Блок if - это валидация аргументов. Если ее не выполнять,
 * то программа будет падать с ошибками не понятными для пользователя.
 * Задание.
 * Доработайте программу ru.job4j.io.Search. Программа должна запускаться с параметрами.
 * Первый параметр - начальная папка. Второй параметр - расширение файлов, которые нужно искать.
 * Необходимо добавить валидацию данных параметров.
 */
public class Search {
    public static void main(String[] args) throws IOException {
        if (args.length == 0 || args.length == 1) {
            throw new IllegalArgumentException(
                    "Enter path for searching and extension of file: /some/path extension"
            );
        }
        Path start = Paths.get(args[0]);
        System.out.println(search(start, p -> p
                .toFile()
                .getName()
                .endsWith(args[1])));
    }

    /**
     * Метод search - это метод поиска файлов в заданной директории, согласно предикату.
     *
     * @param root      Корневая директория.
     * @param condition Предикат поиска файлов.
     * @return Список найденных файлов.
     * @throws IOException При возникновении IO исключений.
     */
    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}
