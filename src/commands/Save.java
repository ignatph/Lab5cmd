package commands;


import collection.CollectionWorker;
import manager.UserManager;
import parse.XmlWriter;
import utillity.Printer;

/**
 * Class contains implementation of print_ascending command
 * Save command in csv file on desktop
 */
public class Save extends Command {

    public Save(String description, boolean hasArgs, UserManager userManager, CollectionWorker workerCollection) {
        super(description, hasArgs, userManager, workerCollection);
    }

    @Override
    public void execute(Printer printer) {
        if (checkArgument(new Printer(), getArgs())) {
            XmlWriter csvWriter = new XmlWriter();
            String filePath =  "src/save.xml";
            csvWriter.writeToXml(collection.getCollection(), filePath);

        }
    }


    @Override
    public boolean checkArgument(Printer printer, Object inputArgs) {
        if (inputArgs == null) {
            return true;
        } else {
            printer.print("У команды save нет аргументов! Попробуйте еще раз");
            return false;
        }
    }
}
