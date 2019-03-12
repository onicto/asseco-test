package hr.asseco.onict.commands.impl;

import hr.asseco.onict.actions.CallableFilterName;
import hr.asseco.onict.commands.Command;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;

public class FilterNameCommand implements Command {
    @Override
    public void executeCommand(ExecutorService executorService, String[] inputArray) {
        if (inputArray.length != 3 && inputArray.length != 2) {
            System.out.println("Greška. Nedostaje relacija.");
        } else {
            String nameStartsWith = inputArray[1];
            String caseUorL = inputArray.length == 3 ? inputArray[2] : null;
            FutureTask filterName = new FutureTask(new CallableFilterName(nameStartsWith, caseUorL));
            executorService.execute(filterName);
        }
    }
}
