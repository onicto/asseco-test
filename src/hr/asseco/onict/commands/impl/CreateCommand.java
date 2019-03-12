package hr.asseco.onict.commands.impl;

import hr.asseco.onict.actions.CallableCreate;
import hr.asseco.onict.commands.Command;
import hr.asseco.onict.model.Student;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;

public class CreateCommand implements Command {
    @Override
    public void executeCommand(ExecutorService executorService, String[] inputArray) {
        if (inputArray.length != 5) {
            System.out.println("Greška. Nedostaje relacija.");
        } else {
            Student newStudent = new Student(inputArray[1], inputArray[2], inputArray[3], Short.valueOf(inputArray[4]));
            FutureTask create = new FutureTask(new CallableCreate(newStudent));
            executorService.execute(create);
        }
    }
}
