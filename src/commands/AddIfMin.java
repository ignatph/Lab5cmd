package commands;

import collection.CollectionWorker;
import manager.UserManager;
import utillity.Printer;

public class AddIfMin extends Command {
    public AddIfMin(String description, boolean hasArgs, UserManager userManager, CollectionWorker workerCollection) {
        super(description, hasArgs, userManager, workerCollection);
    }

    @Override
    public void execute(Printer printer) {
        if (checkArgument(new Printer(), getArgs())) {
            if (collection.getCollection().isEmpty()) {
                printer.print("Коллекция уже пустая");
            } else {
                collection.getCollection().clear();
                printer.print("Коллекция очищена!");
            }
        }
    }


    @Override
    public boolean checkArgument(Printer printer, Object inputArgs) {
        if (inputArgs == null) {
            return true;
        } else {
            printer.print("У команды clear нет аргументов!");
            return false;
        }
    }


}







