package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class ITProgrammer {
    private final boolean oracleCertification;
    private final int monthOfPractice;
    private final IdNumber idNumber;
    private final String[] specialization;

    public ITProgrammer(boolean oracleCertification, int monthOfPractice, IdNumber idNumber, String... specialization) {
        this.oracleCertification = oracleCertification;
        this.monthOfPractice = monthOfPractice;
        this.idNumber = idNumber;
        this.specialization = specialization;
    }

    @Override
    public String toString() {
        return "ITProgrammer{"
                + "oracleCertification=" + oracleCertification
                + ", monthOfPractice=" + monthOfPractice
                + ", idNumber=" + idNumber
                + ", specialization=" + Arrays.toString(specialization)
                + '}';
    }

    public static void main(String[] args) {
        final ITProgrammer itProgrammer = new ITProgrammer(
                false, 8, new IdNumber("12-345"), "java developer");

        /* Преобразуем объект person в json-строку. */
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(itProgrammer));

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
