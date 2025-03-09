package parse;

import body.Worker;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.FileReader;
import java.io.IOException;

public class XmlReader {

    private final XStream xstream;

    public XmlReader() {
        xstream = new XStream(new DomDriver());
        xstream.alias("worker", Worker.class);
    }

    /**
     * Читает XML-файл и десериализует его в объект.
     *
     * @param filePath Путь к XML-файлу.
     * @return Десериализованный объект.
     */
    public Object readFromXml(String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            Object object = xstream.fromXML(reader);
            System.out.println("Данные успешно прочитаны из файла: " + filePath);
            return object;
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
            return null;
        }
    }
}