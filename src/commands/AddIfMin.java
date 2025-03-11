package commands;

import body.*;
import collection.CollectionWorker;
import manager.UserManager;
import utillity.IDGenerator;
import utillity.Printer;

public class AddIfMin extends Command {
    public AddIfMin(String description, boolean hasArgs, UserManager userManager, CollectionWorker workerCollection) {
        super(description, hasArgs, userManager, workerCollection);
    }
    @Override
    public void execute(Printer printer) {
        if (checkArgument(new Printer(), getArgs())) {
            Worker worker = new Worker();
            worker.setId(IDGenerator.generateUniqueId());
            userManager.userDataCollect(worker);
            collection.addWorker(worker);
            printer.print("Объект успешно добавлен в коллекцию!");

        }
    }

    @Override
    public boolean checkArgument(Printer printer, Object inputArgs) {
        if (inputArgs == null) {
            printer.print("У команды AddIFMin должен быть аргумент endDate!");
            return false;
        }
        return true;
    }

}

