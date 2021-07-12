package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AnalizThird {
    public void unavailable(String source, String target) {
        List<String> timeList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(source))) {
            String checkNull;
            String checkBefore = "";
            StringBuilder sb = new StringBuilder();
            do {
                String buff = br.readLine();
                checkNull = buff;
                if (buff != null) {
                    String[] buffArr = buff.split(" ");
                    if (("400".equals(buffArr[0]) || "500".equals(buffArr[0]))
                            && !"400".equals(checkBefore) && !"500".equals(checkBefore)) {
                        sb.append(buffArr[1]);
                        checkBefore = buffArr[0];
                    }
                    if ("".equals(checkBefore)) {
                        checkBefore = buffArr[0];
                    }
                    if(("400".equals(checkBefore) || "500".equals(checkBefore))
                            && ("200".equals(buffArr[0]) || "300".equals(buffArr[0]))) {
                        sb.append(";");
                        sb.append(buffArr[1]);
                        checkBefore = buffArr[0];
                        timeList.add(sb.toString());
                        sb.setLength(0);
                    }
                }
            } while (checkNull != null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        writeResult(timeList, target);
    }

    private void writeResult(List<String> list, String filepath) {
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(filepath)))) {
            for (String s : list) {
                out.write(s);
                out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
