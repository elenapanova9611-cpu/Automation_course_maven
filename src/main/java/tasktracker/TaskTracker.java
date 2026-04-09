package tasktracker;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TaskTracker {
    static final String FILE_PATH = "/Users/alenap/tasks.txt";
    static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) throws IOException {
          /*
        1. Create task model
        2. Show the menu
            2.1 create method that show the menu
        3. if input = 1 --> start creating Task flow
            3.1 created task should be added to the array
            3.2 show menu again
        4. if input = 2 --> Print all tasks stored in array
            4.1 show menu again
        5. if input = 0 --> finish the program

        1. Создать конструктор для enum
            1a. Причесать строку, которую юзер вводит
        2. Создать метод, который переводит значение enum в строку
        3. Если юзер неверно ввел статус --> попросить снова
        4. читаем строку и из строки делаем массив подстрок и из этого фоормируем объект
         */

        readTasksFromFile();
        while (true) {
            showMenu();
            ActionMenu action = ActionMenu.fromInt(getUserInput());
            switch (action) {
                case CREATE:
                    tasks.add(createTask());
                    writeTasksToFile();
                    break;
                case DISPLAY:
                    printAllTasks();
                    break;
                case EXIT:
                    return;
                case UNKNOWN:
                    System.out.println("Invalid input. Please try again.");
                    break;
            }
        }
    }

    public static void showMenu() {
        System.out.println("Select an option:");
        System.out.println("1 - Input the task");
        System.out.println("2 - Display all tasks");
        System.out.println("0 - Exit");
    }

    public static int getUserInput() {
        Scanner userInput = new Scanner(System.in);
        return userInput.nextInt();
    }

    public static Task createTask() {
        Scanner userInput = new Scanner(System.in);

        System.out.println("Fill in the following data");

        System.out.println("1. Task name");
        String taskName = userInput.nextLine();

        System.out.println("2. Task description");
        String taskDescription = userInput.nextLine();

        System.out.println("3. Task priority");

        int taskPriority = 0;
        do {
            try {
                taskPriority = userInput.nextInt();
                userInput.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("You should enter a number");
                userInput.nextLine();
            }
        } while (taskPriority == 0);

        Status taskStatus;
        do {
            System.out.println("4. Task status (Open, In Progress, Ready For Test, In Test, Closed)");
            taskStatus = Status.makeFromUserInput(userInput.nextLine());
        } while (taskStatus == null);

        return new Task(taskName, taskDescription, taskPriority, taskStatus);
    }

    public static void printAllTasks() {
        for (Task task : tasks) {
            System.out.printf(task.getInfo());
        }
    }

    public static String getDataForFileWriter(Task task) {
        return String.format("%s/%s/%d/%s", task.getName(), task.getDescription(), task.getPriority(),
                task.getStatus());
    }

    public static void writeTasksToFile() throws IOException {
        FileWriter fileWriter = new FileWriter(FILE_PATH, false);
        for (Task task : tasks) {
            fileWriter.write(getDataForFileWriter(task) + "\n");
        }
        fileWriter.close();
    }

    public static void readTasksFromFile() throws IOException {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return;
        }

        tasks.clear();

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.isBlank()) {
                    continue;
                }

                String[] substrings = line.split("/");

                String taskName = substrings[0];
                String taskDescription = substrings[1];
                int taskPriority = Integer.parseInt(substrings[2]);
                Status status = Status.makeFromUserInput(substrings[3]);

                if (status == null) {
                    continue;
                }

                Task task = new Task(taskName, taskDescription, taskPriority, status);
                tasks.add(task);
            }
        }
    }
}

