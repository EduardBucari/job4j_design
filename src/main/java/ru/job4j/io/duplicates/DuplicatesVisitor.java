package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import java.util.HashSet;
import java.util.Set;

/**
 * Поиск дубликатов.
 * Нужно написать программу, которая принимает на вход папку,
 * просматривает все файлы в ней (и всех подпапках и под-под-...папках) и сообщает,
 * если находит дубликаты. Дубликаты – это два файла с одинаковым именем и размером.
 *
 * Класс SimpleFileVisitor уже реализует FileVisitor,
 * только переопределяя все методы с указанием на дальнейший обход CONTINUE.
 * Это означает, что унаследовавшись от него мы можем переопределить нужный лишь метод.
 *
 *
 */
public class DuplicatesVisitor  extends SimpleFileVisitor<Path> {
    private final Set<FileProperty> propertyAll = new HashSet<>();

    /**
     * Метод FileVisitResult вызывается каждый раз, когда встречается файл.
     * Используем этот метод, чтобы проверить файл на дубликатность.
     * @param file - Проверяемый файл;
     * @param attrs - Атрибуты проверяемого файла;
     * @return CONTINUE;
     * @throws IOException - Возможно возникновение IO исключений
     */
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws  IOException {
        FileProperty fileProperty = new FileProperty(file
                .toFile()
                .length(), file.getFileName().toString());
        if (propertyAll.contains(fileProperty)) {
            System.out.println(file.toAbsolutePath());
        } else {
            propertyAll.add(fileProperty);
        }
        return super.visitFile(file, attrs);
    }
}
