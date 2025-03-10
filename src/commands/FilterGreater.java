package commands;

import body.Worker;
import collection.CollectionWorker;
import manager.UserManager;
import utillity.Printer;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

public class FilterGreater extends Command {
    public FilterGreater(String description, boolean hasArgs, UserManager userManager, CollectionWorker workerCollection) {
        super(description, hasArgs, userManager, workerCollection);
    }

    @Override
    public void execute(Printer printer) {
        if (checkArgument(printer, getArgs())) {
            try {
                LocalDate targetDate = LocalDate.parse(getArgs().toString());
                List<Worker> filtered = collection.getCollection().stream()
                        .filter(worker -> worker.getEndDate() != null)
                        .filter(worker -> worker.getEndDate().isAfter(targetDate))
                        .collect(Collectors.toList());

                if (filtered.isEmpty()) {
                    printer.print("Нет элементов с endDate > " + targetDate);
                } else {
                    printer.print("Элементы с endDate > " + targetDate + ":");
                    filtered.forEach(worker -> printer.print(worker.toString()));
                }
            } catch (DateTimeParseException e) {
                printer.print("Ошибка формата даты! Используйте формат ГГГГ-ММ-ДД");
            }
        }
    }

    @Override
    public boolean checkArgument(Printer printer, Object inputArgs) {
        if (inputArgs == null) {
            printer.print("У команды filter_greater_than_end_date должен быть аргумент endDate!");
            return false;
        }
        return true;
    }
}