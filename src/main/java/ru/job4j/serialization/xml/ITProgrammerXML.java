package ru.job4j.serialization.xml;

import java.util.Arrays;

public class ITProgrammerXML {
    private final boolean oracleCertification;
    private final int monthOfPractice;
    private final IdNumberXML idNumberXML;
    private final String[] specialization;

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

    public static void main(String[] args) {
        final ITProgrammerXML itProgrammerXML = new ITProgrammerXML(
                false, 8, new IdNumberXML("123-45"), "java developer", "free");
    }
}
