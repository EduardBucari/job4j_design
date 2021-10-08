package ru.job4j.io.scanner;

import ru.job4j.io.ArgsName;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Scanner.
 * Задание:
 * 1. Создать класс CSVReader. Задача класса прочитать данные из CSV файла и вывести их.
 * 2. В качестве входных данных задается путь к файлу path, разделитель delimiter,
 *    приемник данных out и фильтр по столбцам filter.
 *    Ключ -out имеет только два допустимых значения stdout или путь к файлу, куда нужно вывести.
 *    Например, если есть файл CSV со столбцами name, age, birthDate, education, children
 *    и программа запускается таким образом:
 *    java -jar target/csvReader.jar -path=file.txt -delimiter=";"  -out=stdout -filter=name,age
 *    то программа должна прочитать файл по пути file.txt и вывести только столбцы name, age в консоль.
 *    В качестве разделителя данных выступает ;
 * 3. Для чтения аргументов использовать класс Args из задания "5.1. Именованные аргументы".
 * 4. Программа должна запускаться с консоли в виде jar файла.
 * 5. Для чтения файла использовать класс Scanner.
 * 6. Добавить валидацию аргументов, как в классе Search.
 */
public class CSVReader {
    private final Path path;
    private final String delimiter;
    private final String out;
    private final List<String> filter;
    private final  StringBuilder builder = new StringBuilder();


    public CSVReader(Path path, String delimiter, String out, String[] filter) {
        this.path = path;
        this.delimiter = delimiter;
        this.out = out;
        this.filter = List.of(filter);
    }

    public void read() throws IOException {
        final Scanner scanFile = new Scanner(path)
                .useDelimiter(System.lineSeparator());


        final String[] colNames = scanFile.next().split(delimiter);
        final int[] index = IntStream.range(0, colNames.length)
                .filter(i -> filter.contains(colNames[i])).toArray();


        while (scanFile.hasNextLine()) {
            final String[] columns = scanFile.next().split(delimiter);
            for (int i = 0; i < index.length; i++) {
                builder.append(String.format("%s='%s' ",
                        filter.get(i), columns[index[i]]
                ));
            }
            builder.append(System.lineSeparator());
        }
        scanFile.close();
    }

    public void printInfo() throws IOException {
        if (out.equals("stdout")) {
            System.out.println(builder);
        } else {
            try (FileWriter writer = new FileWriter(out)) {
                writer.write(builder.toString());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        final  ArgsName argsName = ArgsName.of(args);
        final Path path = Path.of(argsName.get("path"));


        if (args.length != 4 || !path.toFile().isFile()) {
            throw new IllegalArgumentException("Invalid path: " + path);
        }
        final CSVReader reader = new CSVReader(
                path,
                argsName.get("delimiter"),
                argsName.get("out"),
                argsName.get("filter").split(",")
        );
        reader.read();
        reader.printInfo();
    }
}
