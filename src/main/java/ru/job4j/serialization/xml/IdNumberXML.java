package ru.job4j.serialization.xml;

public class IdNumberXML {
    private String idNumberXML;

    public IdNumberXML() {

    }

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
