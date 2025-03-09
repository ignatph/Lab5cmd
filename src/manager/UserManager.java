package manager;

import collection.CollectionWorker;
import commands.Command;
import manager.*;
import body.*;
import utillity.*;
import validator.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

import utillity.*;

/**
 * Class for working with user.
 * Contains tools for checking and validating input values from user and adding new objects to collection
 */

public class UserManager {
    private HashMap<String, Command> descriptionMap;
    private static boolean flag;
    private final Reader reader;

    public UserManager(Reader reader, CollectionWorker collection) {
        this.reader = reader;
        this.descriptionMap = new CommandsManager(this, collection).getOpis();
    }

    static {
        System.out.println("Приложение запущено!");
        flag = true;

    }

    public static boolean isRunning() {
        return flag;
    }

    public static void setIsInWork(boolean flag) {
        UserManager.flag = flag;
    }


    /**
     * Method for requesting user for command
     */
    public void requestInputCommand() {
        try {
            System.out.print("\nВведите команду: ");
            String line = reader.nextLine().strip().replaceAll("\\s+", " ");
            System.out.println(line);
            // (\\s+) один или несколько символов пробела, табуляции, новой строки и тд
            checkAndStartCommand(line);
        } catch (NoSuchElementException ex) {
            System.out.println("Завершение программы!");
            setIsInWork(false);

        }
    }

    /**
     * Method for working with script from user file
     *
     * @param list
     */
    public void requestCommandForScript(List<String> list) {
        try {
            for (String command : list) {
                command = command.replaceAll("\\s+", " ").trim().strip();
                System.out.println("\nСейчас выполняется команда " + command);
                RecursionLimiter.emerge();
                checkAndStartCommand(command);
            }
        } catch (StackOverflowError | RecursionException ex) {
            System.err.println("\nСкрипт вызывает сам себя! Выход из скрипта");
        }
    }

    /**
     * Method for validating input command and arguments
     */
    public void checkAndStartCommand(String line) {
        String argument;
        String command;
        String[] inputData = line.split(" ");

        if (inputData.length == 1) {
            argument = null;
            command = inputData[0].toLowerCase();
        } else if (inputData.length == 2) {
            command = inputData[0].toLowerCase();
            argument = inputData[1];
        } else {
            System.out.println("Команда/аргумент введены некорректно! Повторите попытку");
            return;
        }
        if (descriptionMap.containsKey(command)) {
            descriptionMap.get(command).setArgs(argument);
            descriptionMap.get(command).execute(new Printer()); //запуск команды
        } else {
            System.out.println("Команды: " + inputData[0] + " – не существует! Для справки команд введите: \"help\" ");
        }
    }


    /**
     * Main method in class, contains request from user to fill the collection.
     * Validates all the fields which return as a final value to collection
     *
     * @param worker
     * @return
     */
    public Worker userDataCollect(Worker worker) {
        Scanner reader = new Scanner(System.in);
        String userInput;

        do {
            System.out.print("Введите имя работника: ");
            userInput = reader.nextLine().strip();
            if (!userInput.isEmpty()) {
                worker.setName(userInput);
            } else {
                System.out.println("Ошибка: Имя не может быть пустым!");
            }
        } while (!NameValidator.validate(worker.getName()));

        // Ввод координаты X
        Float x = null;
        do {
            System.out.print("Введите координату X (число > -848): ");
            userInput = reader.nextLine().strip();
            try {
                x = Float.parseFloat(userInput.replace(",", "."));
                if (x <= -848) {
                    System.out.println("Ошибка: X должен быть больше -848!");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: Некорректный формат числа!");
            }
        } while (!XValidator.validate(x));

        // Ввод координаты Y
        Float y = null;
        do {
            System.out.print("Введите координату Y: ");
            userInput = reader.nextLine().strip();
            try {
                y = Float.parseFloat(userInput);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: Некорректный формат числа!");
            }
        } while (!YValidator.validate(y));

        worker.setCoordinates(new Coordinates(x, y));

        // Ввод зарплаты
        Integer salary = null;
        do {
            System.out.print("Введите зарплату (> 0): ");
            userInput = reader.nextLine().strip();
            try {
                salary = Integer.parseInt(userInput);
                if (salary <= 0) {
                    System.out.println("Ошибка: Зарплата должна быть больше 0!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: Некорректный формат числа!");
            }
        } while (!SalaryValidator.validate(salary));
        worker.setSalary(salary);

        // Ввод статуса
        Status status = null;
        do {
            System.out.print("Введите статус (" + Arrays.toString(Status.values()) + "): ");
            userInput = reader.nextLine().strip().toUpperCase();
            try {
                status = Status.valueOf(userInput);
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: Некорректный статус!");
            }
        } while (!StatusValidator.validate(status));
        worker.setStatus(status);

        // Ввод организации
        Organization organization = new Organization();

        // Название организации (может быть null)
        System.out.print("Введите название организации (или 'null'): ");
        String orgName = reader.nextLine().strip();
        if (orgName.equalsIgnoreCase("null")) orgName = null;
        organization.setName(orgName);

        // Тип организации
        OrganizationType orgType = null;
        do {
            System.out.print("Введите тип организации (" + Arrays.toString(OrganizationType.values()) + "): ");
            userInput = reader.nextLine().strip().toUpperCase();
            try {
                orgType = OrganizationType.valueOf(userInput);
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: Некорректный тип организации!");
            }
        } while (!OrganizationValidator.validate(orgType));
        organization.setType(orgType);

        // Адрес
        Address address = new Address();

        // Улица
        do {
            System.out.print("Введите улицу: ");
            userInput = reader.nextLine().strip();
            if (userInput.isEmpty()) {
                System.out.println("Ошибка: Улица не может быть пустой!");
            } else {
                address.setStreet(userInput);
            }
        } while (!StreetValidator.validate(address.getStreet()));

        // Почтовый индекс
        String zipCode = null;
        do {
            System.out.print("Введите почтовый индекс (минимум 6 символов или 'null'): ");
            userInput = reader.nextLine().strip();
            if (userInput.equalsIgnoreCase("null")) break;
            if (userInput.length() < 6) {
                System.out.println("Ошибка: Минимум 6 символов!");
                continue;
            }
            zipCode = userInput;
        } while (!ZipCodeValidator.validate(zipCode));
        address.setZipCode(zipCode);

        organization.setAddress(address);
        worker.setOrganization(organization);

        // Опциональные поля
        // Дата окончания
        LocalDate endDate = null;
        do {
            System.out.print("Введите дату окончания (ГГГГ-ММ-ДД или 'null'): ");
            userInput = reader.nextLine().strip();
            if (userInput.equalsIgnoreCase("null")) break;
            try {
                endDate = LocalDate.parse(userInput);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Ошибка формата даты!");
            }
        } while (true);
        worker.setEndDate(endDate);

        // Должность
        Position position = null;
        do {
            System.out.print("Введите должность (" + Arrays.toString(Position.values()) + " или 'null'): ");
            userInput = reader.nextLine().strip().toUpperCase();
            if (userInput.equalsIgnoreCase("null")) break;
            try {
                position = Position.valueOf(userInput);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: Некорректная должность!");
            }
        } while (true);
        worker.setPosition(position);

        // Автоматические поля
        worker.setCreationDate(LocalDate.now());
        worker.setId(IDGenerator.generateUniqueId());

        return worker;
    }
}