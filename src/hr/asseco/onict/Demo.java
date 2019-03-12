package hr.asseco.onict;

import hr.asseco.onict.commands.Command;
import hr.asseco.onict.commands.impl.*;
import hr.asseco.onict.db.DbStudent;
import hr.asseco.onict.model.Student;
import hr.asseco.onict.reader.StudentReader;
import hr.asseco.onict.reader.impl.StudentCSVReader;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demo {

    private static Map<String, Command> commands = new HashMap<>();
    static {
        commands.put("exit", new ExitCommand());
        commands.put("create", new CreateCommand());
        commands.put("read", new ReadCommand());
        commands.put("filter-grade", new FilterGradeCommand());
        commands.put("filter-name", new FilterNameCommand());
    }

    public static void main(String[] args) {
        String csvPath = args[0];
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        if (args.length != 1) {
            System.out.println("Pogrešan broj parametara programa.");
            return;
        }

        StudentReader reader = new StudentCSVReader();

        ConcurrentHashMap<String, Student> initialMap = reader.readStudentsToMap(csvPath);

        System.out.println("Dobrodošao dragi korisniče.");

        DbStudent dbStudent = DbStudent.getInstance();
        dbStudent.init(initialMap);

        Scanner scanner = new Scanner(System.in);

        while(true) {
            String input = scanner.nextLine();

            if (!input.isEmpty()) {
                String[] inputArray = input.split(" ");
                if (commands.containsKey(inputArray[0].toLowerCase())) {
                    commands.get(inputArray[0].toLowerCase()).executeCommand(executorService, inputArray);
                }
            }
        }
    }
}
