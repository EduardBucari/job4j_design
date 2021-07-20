package ru.job4j.io;

import java.io.*;
import java.util.List;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipThird {
    public static void packFiles(List<File> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File source : sources) {
                zip.putNextEntry(new ZipEntry(source.getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

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
        packSingleFile(
                new File("./chapter_005/pom.xml"),
                new File("./chapter_005/pom.zip")
        );
    }
}
