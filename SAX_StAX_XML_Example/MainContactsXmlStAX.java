import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class MainContactsXmlStAX {
    public static void main(String[] args) {


        List<Contact> persList = new ArrayList<>();
        Contact pers = null;

        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        try {
            // инициализируем reader и скармливаем ему xml файл
            XMLEventReader reader = xmlInputFactory.createXMLEventReader(new FileInputStream("Contacts.xml"));

            // проходим по всем элементам xml файла
            while (reader.hasNext()) {
                 // получаем событие (элемент) и разбираем его
                XMLEvent xmlEvent = reader.nextEvent();

                if (xmlEvent.isStartElement()) {
                    StartElement startElement = xmlEvent.asStartElement();
                    if (startElement.getName().getLocalPart().equals("contact")) {
                        pers = new Contact();
                        // Получаем атрибуты для каждого элемента Student
                        Attribute persAttr = startElement.getAttributeByName(new QName("type"));
                        if (persAttr != null) {
                            pers.setType(persAttr.getValue());
                        }
                        persAttr = startElement.getAttributeByName(QName.valueOf("weight"));
                        if (persAttr != null) {
                            pers.setWeight(Integer.parseInt(persAttr.getValue()));
                        }
                    } else if (startElement.getName().getLocalPart().equals("firstName")) {
                        xmlEvent = reader.nextEvent();
                        pers.setFirstName(xmlEvent.asCharacters().getData());
                    } else if (startElement.getName().getLocalPart().equals("lastName")) {
                        xmlEvent = reader.nextEvent();
                        pers.setLastName(xmlEvent.asCharacters().getData());
                    } else if (startElement.getName().getLocalPart().equals("email")) {
                        xmlEvent = reader.nextEvent();
                        pers.setEmail(xmlEvent.asCharacters().getData());
                    }
                }
                // если цикл дошел до закрывающего элемента Contact,
                // то добавляем считанного из файла персонажа в список
                if (xmlEvent.isEndElement()) {
                    EndElement endElement = xmlEvent.asEndElement();
                    if (endElement.getName().getLocalPart().equals("contact")) {
                        persList.add(pers);
                    }
                }
            }

        } catch (FileNotFoundException | XMLStreamException exc) {
            exc.printStackTrace();
        }
for(Contact c : persList)
    System.out.println(c);
    }
}
