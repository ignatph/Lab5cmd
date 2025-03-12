package main;


import manager.UserManager;
import parse.XmlReader;

import utillity.Printer;
import utillity.Reader;
import collection.CollectionWorker;

import java.time.LocalDateTime;


//add John 100.5 200.0 50000 HIRED Company COMMERCIAL MainStreet 123456
public class Main {
    public static String FILE_PATH = "";

    public static void main(String[] args) {
        Reader reader = new Reader();
        Printer printer = new Printer();

        System.out.println("–––––––– " + LocalDateTime.now().toString().substring(0, 10) + " ––––––––");
        System.out.println("Введите путь к файлу или нажмите Enter чтобы продолжить");
        String userInp = reader.nextLine();
        if (userInp == null) {
            System.out.println("\n");
        } else {
            FILE_PATH = userInp;
        }
        CollectionWorker worker = new CollectionWorker();
        XmlReader reader1 = new XmlReader(printer ,new CollectionWorker());
        reader1.read(FILE_PATH);
        printer.print(worker.getCollection().toString());
        UserManager userManager = new UserManager(reader, worker);
        UserManager.setIsInWork(true);
        while (UserManager.isRunning()) {
            userManager.requestInputCommand();
        }
    }
}
