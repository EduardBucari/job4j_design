package ru.job4j.io.search;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

/**
 * Поиск файлов по критерию.
 * Задание:
 * 1. Создать программу для поиска файла. Все классы, относящиеся к этому заданию должны быть в отдельном пакете.
 * 2. Программа должна искать данные в заданном каталоге и подкаталогах.
 * 3. Имя файла может задаваться, целиком, по маске, по регулярному выражению(не обязательно).
 * 4. Программа должна собираться в jar и запускаться через java -jar find.jar -d=c:/ -n=*.txt -t=mask -o=log.txt
 * Ключи
 * -d - директория, в которой начинать поиск.
 * -n - имя файла, маска, либо регулярное выражение.
 * -t - тип поиска: mask искать по маске, name по полному совпадение имени, regex по регулярному выражению.
 * -o - результат записать в файл.
 * 5. Программа должна записывать результат в файл.
 * 6. В программе должна быть валидация ключей и подсказка.
 */
public class Searching {

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            throw new IllegalArgumentException("Right format: java -jar ScriptName -d=directory -n=filename -t=typeSearch -o=result");
        }
        ArgsName argN = ArgsName.of(args);
        if (argN.getLength() != 4) {
            throw new IllegalArgumentException("Right format: java -jar ScriptName -d=directory -n=filename -t=typeSearch -o=result");
        }
        String nameSaveLog = argN.get("o");
        Path start = Paths.get(argN.get("d"));
        List<Path> buff = search(start, predicateForming(argN));
        writeResult(nameSaveLog, buff);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFile searcher = new SearchFile(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static void writeResult(String nameLog, List<Path> resultList) {
        try (PrintWriter pr = new PrintWriter(new BufferedOutputStream(new FileOutputStream(nameLog)))) {
           for (Path elem : resultList) {
               pr.write(elem.toString());
               pr.println();
           }
        } catch (Exception e) {
        }
        try (BufferedReader br = new BufferedReader(new FileReader(nameLog))) {
            br.lines().forEach(System.out :: println);
        } catch (Exception e) {
        }
    }

    public static Predicate<Path> predicateForming(ArgsName args) {
        String typeSearch = args.get("t");
        String nameFileSearch = args.get("n");
        Predicate<Path> predicate = null;
        Pattern pattern;
        if (typeSearch.equals("name")) {
            predicate = path -> path.toFile().getName().equals(nameFileSearch);
        } else if (typeSearch.equals("mask")) {
            String value = nameFileSearch.replaceAll("\\*", "(\\\\S\\*)");
            value = value.replaceAll("\\?", "(\\\\S\\?)");
            pattern = Pattern.compile(value);
            predicate = path -> path.toFile().getName().matches(pattern.toString());
        } else if (typeSearch.equals("regex")) {
            pattern = Pattern.compile(nameFileSearch);
            predicate = path -> path.toFile().getName().matches(pattern.toString());
        }
        return predicate;
    }
}