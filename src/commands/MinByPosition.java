package commands;

import body.Worker;
import collection.CollectionWorker;
import manager.UserManager;
import utillity.Printer;

public class MinByPosition extends Command {
    public MinByPosition(String description, boolean hasArgs, UserManager userManager, CollectionWorker workerCollection) {
        super(description, hasArgs, userManager, workerCollection);
    }

    @Override
    public void execute(Printer printer) {
    }

    @Override
    public boolean checkArgument(Printer printer, Object inputArgs) {
        return true;
    }
}
