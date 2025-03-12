package parse;

import collection.CollectionWorker;

import body.*;
import utillity.Printer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;
import java.io.File;
import java.time.LocalDate;

public class XmlReader {
    private final Printer printer;
    private final CollectionWorker collection;

    public XmlReader(Printer printer, CollectionWorker collection) {
        this.printer = printer;
        this.collection = collection;
    }
    public Worker[] read(String path) {
        try {
            File xmlFile = new File(path);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            NodeList workerList = doc.getElementsByTagName("Worker");

            for (int i = 0; i < workerList.getLength(); i++) {
                Node node = workerList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element workerElement = (Element) node;
                    Worker worker = new Worker();

                    // id (предполагается, что id корректно преобразуется)
                    worker.setId(Long.parseLong(getTagValue("id", workerElement)));
                    // name
                    worker.setName(getTagValue("name", workerElement));

                    // coordinates
                    Element coordElement = (Element) workerElement.getElementsByTagName("coordinates").item(0);
                    Coordinates coordinates = new Coordinates();
                    coordinates.setX(Float.parseFloat(getTagValue("x", coordElement)));
                    coordinates.setY(Float.parseFloat(getTagValue("y", coordElement)));
                    worker.setCoordinates(coordinates);

                    // creationDate
                    worker.setCreationDate(LocalDate.parse(getTagValue("creationDate", workerElement)));
                    // salary
                    worker.setSalary(Integer.parseInt(getTagValue("salary", workerElement)));

                    // endDate (может быть пустой)
                    String endDateStr = getTagValue("endDate", workerElement);
                    worker.setEndDate(endDateStr.isEmpty() ? null : LocalDate.parse(endDateStr));

                    // position (опционально)
                    String positionStr = getTagValue("position", workerElement);
                    worker.setPosition(positionStr.isEmpty() ? null : Position.valueOf(positionStr));

                    // status
                    worker.setStatus(Status.valueOf(getTagValue("status", workerElement)));

                    // organization
                    Element orgElement = (Element) workerElement.getElementsByTagName("organization").item(0);
                    Organization organization = new Organization();
                    String fullName = getTagValue("fullName", orgElement);
                    organization.setName(fullName.isEmpty() ? null : fullName);

                    String typeStr = getTagValue("type", orgElement);
                    organization.setType(typeStr.isEmpty() ? null : OrganizationType.valueOf(typeStr));

                    // officialAddress (опционально)
                    NodeList addrList = orgElement.getElementsByTagName("officialAddress");
                    if (addrList.getLength() > 0) {
                        Element addrElement = (Element) addrList.item(0);
                        Address address = new Address();
                        address.setStreet(getTagValue("street", addrElement));
                        address.setZipCode(getTagValue("zipCode", addrElement));
                        organization.setAddress(address);
                    } else {
                        organization.setAddress(null);
                    }

                    worker.setOrganization(organization);

                    collection.addWorker(worker);
                }
            }
        } catch (Exception e) {
            printer.print("Ошибка при чтении XML файла: " + e.getMessage());
        }
        return collection.getCollection().toArray(new Worker[0]);
    }

    // Вспомогательный метод для получения значения элемента по тегу
    private String getTagValue(String tag, Element element) {
        NodeList nlList = element.getElementsByTagName(tag);
        if (nlList != null && nlList.getLength() > 0) {
            Node node = nlList.item(0);
            if (node.getFirstChild() != null) {
                return node.getFirstChild().getNodeValue();
            }
        }
        return "";
    }
}
