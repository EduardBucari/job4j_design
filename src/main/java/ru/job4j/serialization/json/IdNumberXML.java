package ru.job4j.serialization.json;

public class IdNumberXML {

    private final String idNumberXML;

    public String getIdNumberXML() {
        return idNumberXML;
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
