package ru.job4j.io;

import java.io.FileOutputStream;

/**
 * FileOutputStream.
 * Расчет и побитовый вывод таблицы умножения в файл.
 */
public class ResultFile {

    /**
     * Метод multiple возвращает таблицу умножения и выводит файл.
     *
     * LineSeparator () — это встроенный метод в Java,
     * который возвращает системно-зависимую строку разделителя строк.
     * Он всегда возвращает одно и то же значение — начальное значение
     * системного свойства line.separator.
     *
     * @param size - размер таблицы.
     * @return таблица.
     */
    public int[][] multiple(int size) {

      int[][] table = new int[size][size];
      try (FileOutputStream out = new FileOutputStream("../chapter_002/data/multiple.txt")) {
          for (int i = 0; i < size; i++) {
              out.write(System.lineSeparator().getBytes());
              for (int j = 0; j < size; j++) {
                  table[j][i] = (j + 1) * (i + 1);
                  String s = String.valueOf((j + 1) * (i + 1));
                  out.write(s.getBytes());
              }
          }
      } catch (Exception e) {
          e.printStackTrace();
      }
      return table;
    }
}
