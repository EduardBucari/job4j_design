package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class ITProgrammerXML {
    private final boolean oracleCertification;
    private final int monthOfPractice;
    private final IdNumberXML idNumberXML;
    private final String[] specialization;

    public boolean isOracleCertification() {
        return oracleCertification;
    }

    public int getMonthOfPractice() {
        return monthOfPractice;
    }

    public IdNumberXML getIdNumberXML() {
        return idNumberXML;
    }

    public String[] getSpecialization() {
        return specialization;
    }

    public ITProgrammerXML(boolean oracleCertification, int monthOfPractice, IdNumberXML idNumberXML, String... specialization) {
        this.oracleCertification = oracleCertification;
        this.monthOfPractice = monthOfPractice;
        this.idNumberXML = idNumberXML;
        this.specialization = specialization;
    }

    @Override
    public String toString() {
        return "ITProgrammerXML{"
                + "oracleCertification=" + oracleCertification
                + ", monthOfPractice=" + monthOfPractice
                + ", idNumberXML=" + idNumberXML
                + ", specialization=" + Arrays.toString(specialization)
                + '}';
    }

    /**
     * Реализация Json.
     * @param args Аргументы командной строки.
     */
    public static void main(String[] args) {
        final ITProgrammerXML itProgrammerXML = new ITProgrammerXML(
                false, 8, new IdNumberXML("12-345"), "java developer");

        /* Преобразуем объект person в json-строку. */
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(itProgrammerXML));

        /* Модифицируем json-строку */
        final String itProgrammerJson =
                "{"
                        + "\"oracleCertification\":false,"
                        + "\"monthOfPractice\":8,"
                        + "\"idNumber\":"
                        + "{"
                        + "\"idNumber\":\"12-345\""
                        + "},"
                        + "\"specialization\":"
                        + "[\"java developer\",\"Free\"]"
                        + "}";
        final ITProgrammer itProgrammerMod = gson.fromJson(itProgrammerJson, ITProgrammer.class);
        System.out.println(itProgrammerMod);

    }
}
