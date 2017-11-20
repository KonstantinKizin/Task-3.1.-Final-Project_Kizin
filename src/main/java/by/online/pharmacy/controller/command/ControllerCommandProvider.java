package by.online.pharmacy.controller.command;

import by.online.pharmacy.controller.exception.ControllerException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import static by.online.pharmacy.dao.impl.PropertyManager.getProperty;

public class ControllerCommandProvider implements Command {

    private Map<String , Command> commandMap = new HashMap<>();;

    @Override
    public CommandReturnObject execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {

        String commandName = request.getParameter(getProperty("HIDDEN_PARAMETER"));
        Command command = commandMap.get(commandName);
        return command.execute(request,response);
    }

    public Map<String, Command> getCommandMap() {
        return commandMap;
    }




}
