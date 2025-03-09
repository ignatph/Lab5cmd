package commands;

import collection.CollectionWorker;
import manager.UserManager;
import utillity.Printer;

public class FilterGreater extends Command {
    public FilterGreater(String description, boolean hasArgs, UserManager userManager, CollectionWorker workerCollection) {
        super(description, hasArgs, userManager, workerCollection);
    }

    @Override
    public void execute(Printer printer) {
    }

    @Override
    public boolean checkArgument(Printer printer, Object inputArgs) {
        if (inputArgs == null) {
            printer.print("У команды filter_by_group_admin должен быть аргумент!");
            return false;
        } else {
            return true;
        }
    }
}
