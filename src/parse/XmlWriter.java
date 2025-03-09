package parse;

import body.Worker;
import collection.CollectionWorker;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import utillity.Printer;

import java.io.FileWriter;
import java.io.IOException;

public class XmlWriter {

    private final XStream xstream;

    public XmlWriter(){

    }
    {
        xstream = new XStream(new DomDriver());
        // Настройка XStream (например, алиасы для классов)
        xstream.alias("worker", Worker.class); // Пример алиаса для класса Worker
    }

    /**
     * Сериализует объект в XML и записывает его в файл.
     *
     * @param object Объект для сериализации.
     * @param filePath Путь к файлу, в который будет записан XML.
     */
    public void writeToXml(Object object, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            xstream.toXML(object, writer);
            System.out.println("Данные успешно записаны в файл: " + filePath);
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }
}