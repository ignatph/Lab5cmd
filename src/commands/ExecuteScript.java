package commands;

import utillity.Printer;
import collection.CollectionWorker;
import manager.UserManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExecuteScript extends Command {
    public ExecuteScript(String description, boolean hasArgs, UserManager userManager, CollectionWorker workerCollection) {
        super(description, hasArgs, userManager, workerCollection);
    }

    public void execute(Printer printer) {
        if (checkArgument(new Printer(), getArgs())) {
            String base = "";
            String path = base + getArgs();
            try {
                if (new File(path).length() == 0) {
                    printer.print("Скрипт пустой!");
                } else {
                    BufferedReader bf = new BufferedReader(new FileReader(path));
                    String line = bf.readLine();
                    List<String> listOfCommands = new ArrayList<>();
                    while (line != null) {
                        listOfCommands.add(line);
                        line = bf.readLine();
                    }
                    bf.close();
                    userManager.requestCommandForScript(listOfCommands);
                }
            } catch (IOException ex) {
                printer.print("Такого файла не существует! Проверьте, что файл находится в папке");
            }
        }
    }

    @Override
    public boolean checkArgument(Printer printer, Object inputArgs) {
        if (inputArgs == null) {
            printer.print("Команда execute_script должна принимать аргумент в виде пути к файлу!");
            return false;
        }
        return true;
    }
}
