package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "iTProgrammerXML")
@XmlAccessorType(XmlAccessType.FIELD)
public class ITProgrammerXML {

    @XmlAttribute
    private boolean oracleCertification;

    @XmlAttribute
    private int monthOfPractice;
    private IdNumberXML idNumberXML;

    private String[] specialization;

    public ITProgrammerXML() {

    }

    /**
     * JAXB. Преобразование XML в POJO.
     * В этом уроке мы разберемся, как сериализовать/десериализовать объекты в/c XML.
     */
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

    public static void main(String[] args) throws JAXBException {

        final ITProgrammerXML itProgrammerXML = new ITProgrammerXML(
                false, 8, new IdNumberXML("123-45"), "java developer", "free");

        // Получаем контекст для доступа к АПИ
        JAXBContext context = JAXBContext.newInstance(ITProgrammerXML.class);
        // Создаем сериализатор
        Marshaller marshaller = context.createMarshaller();
        // Указываем, что нам нужно форматирование
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        try (StringWriter writer = new StringWriter()) {
            // Сериализуем
            marshaller.marshal(itProgrammerXML, writer);
            String result = writer.getBuffer().toString();
            System.out.println(result);
        } catch (Exception e) {

            /**
             *   Для десериализации нам нужно создать десериализатор
             *         Unmarshaller unmarshaller = context.createUnmarshaller();
             *         try (StringReader reader = new StringReader(xml)) {
             *             // десериализуем
             *           ITProgrammerXML result = (ITProgrammerXML) unmarshaller.unmarshal(reader);
             *             System.out.println(result);
             */

        }
    }
}
