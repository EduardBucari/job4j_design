package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Архивировать проект.
 * Задание:
 * Реализовать класс Zip и написать утилиту для архивации папки.
 *
 * Техническое задание.
 * 1. При запуске указывается папка, которую мы хотим архивировать, например: c:\project\job4j\
 * 2. В качестве ключа передается  расширения файлов, которые не нужно включать в архив.
 * 3. Архив должен сохранять структуру проекта. То есть содержать подпапки.
 * 4. Запуск проекта.
 *
 * java -jar pack.jar -d=c:\project\job4j\ -e=class -o=project.zip
 * java -jar pack.jar - Это собранный jar.
 *
 * -d - directory - которую мы хотим архивировать
 * -e - exclude - исключить файлы *.xml
 * -o - output - во что мы архивируем.
 *
 * 5. Для работы с входными аргументами используйте класс ArgsName из прошлого задания.
 *
 * Важно! Перед запуском проекта нужно делать валидацию аргументов, проверив что они все присутствуют.
 * Также нужно проверить, что архивируемая директория существует.
 *
 * 6. Для архивации использовать классы ZipOutputStream.java.
 *
 * Для поиска и фильтрации файлов нужно использовать класс Search из задания "Сканирование файловой системы".
 *
 * 7. Для тестирования кода в IDEA нужно прописать параметры запуска main метода
 * Run - Edit
 * (В этой задаче используйте вместо File - Path.)
 */
public class Zip {
    public static List<Path> listFiles = new ArrayList<>();

    /**
     * Метод packFiles() архивирует файлы, путь и имя которых
     * получаем через аргумент в виде списка.
     *
     * @param source Список архивируемых файлов.
     * @param target  Имя и расширение архивного файла.
     */
    public void packFiles(List<Path> source, Path target) {
            for (Path file : source) {
                packSingleFile(file, target);
            }
    }

    public void packSingleFile(Path source, Path target) {
        try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(new FileOutputStream(String.valueOf(target))))) {
            zip.putNextEntry(new ZipEntry(source.toFile().getPath()));
            try (BufferedInputStream out = new BufferedInputStream(
                    new FileInputStream(String.valueOf(source)))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            throw new IllegalArgumentException(
                    "Enter: -d directory for archiving, -e exclude file, -o output archive file"
            );
        }
        ArgsName argsMap = ArgsName.of(args);
        Set<String> argSet = argsMap.getKeys();
        for (String key : argSet) {
            if (!"d".equals(key) && !"e".equals(key) && !"o".equals(key)) {
                throw new IllegalArgumentException("-d directory, -e exclude, -o output");
            }
        }
        listFiles = Search.search(Path.of(argsMap.get("d")), p -> !p.toFile().getName().endsWith(argsMap.get("e")));
        new Zip().packFiles(listFiles, Path.of(argsMap.get("o")));
    }
}
