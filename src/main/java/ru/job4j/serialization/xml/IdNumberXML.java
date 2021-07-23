package ru.job4j.serialization.xml;

public class IdNumberXML {
    private final String idNumberXML;

    public IdNumberXML(String idNumberXML) {
        this.idNumberXML = idNumberXML;
    }

    @Override
    public String toString() {
        return "IdNumberXML{"
                + "idNumberXML='" + idNumberXML + '\''
                + '}';
    }
}
