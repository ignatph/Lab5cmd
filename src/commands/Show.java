package commands;

import body.Worker;
import collection.CollectionWorker;
import manager.UserManager;
import utillity.Printer;
/**
 * Class contains implementation of show command
 * Show all elements in collection
 */
public class Show extends Command {
    public Show(String description, boolean hasArgs, UserManager userManager, CollectionWorker workerCollection) {
        super(description, hasArgs, userManager, workerCollection);
    }

    @Override
    public void execute(Printer printer) {
        System.out.println(CollectionWorker.getCollection());
    }

    @Override
    public boolean checkArgument(Printer printer, Object inputArgs) {
        if (inputArgs == null) {
            return true;
        } else {
            printer.print("У команды show нет аргументов!");
            return false;
        }
    }
}
