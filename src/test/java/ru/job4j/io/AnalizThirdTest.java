package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class AnalizThirdTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
   public void whenTwoPeriodUnavailable() throws IOException {
        File source = folder.newFile("server.log");
        File target = folder.newFile("unavailable.log");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01");
            out.println("200 10:57:01");
            out.println("400 10:58:01");
            out.println("200 10:59:01");
            out.println("500 11:01:02");
            out.println("200 11:02:02");
        }
        AnalizThird analizThird = new AnalizThird();
        analizThird.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(s -> rsl.append(s).append(System.lineSeparator()));
        }
        assertThat(rsl.toString(), is(
                "10:58:01;10:59:01"
                        + System.lineSeparator()
                        + "11:01:02;11:02:02"
                        + System.lineSeparator()
        ));
    }

    @Test
    public void whenOnePeriodUnavailable() throws IOException {
        File source = folder.newFile("server.log");
        File target = folder.newFile("unavailable.log");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01");
            out.println("200 10:57:01");
            out.println("400 10:58:01");
            out.println("200 10:59:01");
        }
        AnalizThird analizThird = new AnalizThird();
        analizThird.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(s -> rsl.append(s).append(System.lineSeparator()));
        }
        assertThat(rsl.toString(), is(
                "10:58:01;10:59:01" + System.lineSeparator()
        ));
    }

    @Test
    public void whenNoPeriodUnavailable() throws IOException {
        File source = folder.newFile("server.log");
        File target = folder.newFile("unavailable");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01");
            out.println("200 10:57:01");
            out.println("300 10:58:01");
            out.println("200 10:59:01");
        }
        AnalizThird analizThird = new AnalizThird();
        analizThird.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(s -> rsl.append(s).append(System.lineSeparator()));
        }
        assertThat(rsl.toString(), isEmptyString());
    }
}