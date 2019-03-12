package hr.asseco.onict.commands;

import java.util.concurrent.ExecutorService;

public interface Command {
    public void executeCommand(ExecutorService executorService, String[] inputArray);
}
