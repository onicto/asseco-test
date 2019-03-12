package hr.asseco.onict.commands.impl;

import hr.asseco.onict.actions.CallableRead;
import hr.asseco.onict.commands.Command;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;

public class ReadCommand implements Command {
    @Override
    public void executeCommand(ExecutorService executorService, String[] inputArray) {
        if (inputArray.length != 2) {
            System.out.println("Greška. Nedostaje relacija.");
        } else {
            String jmbag = inputArray[1];
            FutureTask read = new FutureTask(new CallableRead(jmbag));
            executorService.execute(read);
        }
    }
}
