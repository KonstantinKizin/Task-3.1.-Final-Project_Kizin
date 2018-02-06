package by.online.pharmacy.controller.command;

import java.util.HashMap;
import java.util.Map;


public final class CommandProvider {

    private final Map<String , Command> commandMap = new HashMap<>();

    private final static CommandProvider instance = new CommandProvider();

    private CommandProvider(){

    }

    public Map<String, Command> getCommandMap() {
        return commandMap;
    }

    public static CommandProvider getInstance() {
        return instance;
    }
}
