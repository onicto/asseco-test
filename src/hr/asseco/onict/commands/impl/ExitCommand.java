package hr.asseco.onict.commands.impl;

import hr.asseco.onict.commands.Command;

import java.util.concurrent.ExecutorService;

public class ExitCommand implements Command {
    @Override
    public void executeCommand(ExecutorService executorService, String[] inputArray) {
        executorService.shutdown();
        System.exit(0);
    }
}
