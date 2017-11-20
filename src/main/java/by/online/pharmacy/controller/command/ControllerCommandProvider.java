package by.online.pharmacy.controller.command;

import by.online.pharmacy.controller.exception.ControllerException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerCommandProvider implements Command {

    private Map<String , Command> commandMap = new HashMap<>();
    private final String HIDDEN_PARAMETER = "hidden";

    @Override
    public CommandReturn execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException , IOException {

        String commandName = request.getParameter(HIDDEN_PARAMETER);
        Command command = commandMap.get(commandName);
        return command.execute(request,response);
    }

    public Map<String, Command> getCommandMap() {
        return commandMap;
    }




}
