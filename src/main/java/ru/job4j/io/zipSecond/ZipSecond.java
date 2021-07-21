package ru.job4j.io.zipSecond;

import ru.job4j.io.SearchFiles;
import ru.job4j.io.Zip;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipSecond {
    public void packFiles(List<File> sources, File target) {
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

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {

        ArgZipSecond argZipSecond = new ArgZipSecond(args);
        if (argZipSecond.valid()) {
            Path root = Paths.get(argZipSecond.directory());
            List<Path> sourcesPath = searchExclude(root, argZipSecond.exclude());
            List<File> sources = sourcesPath.stream()
                    .map(Path::toFile)
                    .collect(Collectors.toList());
            new Zip().packFiles(sources, new File(argZipSecond.output()));
        }
    }

    public static List<Path> searchExclude(Path root, String ext) throws IOException {
        SearchFiles searcher = new SearchFiles(
                p -> !p.toFile().getName().endsWith(ext.substring(1))
        );
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();


    }
}
