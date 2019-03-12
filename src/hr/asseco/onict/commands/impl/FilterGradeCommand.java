package hr.asseco.onict.commands.impl;

import hr.asseco.onict.actions.CallableFilterGrade;
import hr.asseco.onict.commands.Command;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;

public class FilterGradeCommand implements Command {
    @Override
    public void executeCommand(ExecutorService executorService, String[] inputArray) {
        if (inputArray.length != 3) {
            System.out.println("Greška. Nedostaje relacija.");
        } else {
            Short grade = Short.valueOf(inputArray[1]);
            String condition = inputArray[2];
            FutureTask filterGrade = new FutureTask(new CallableFilterGrade(grade, condition));
            executorService.execute(filterGrade);
        }
    }
}
