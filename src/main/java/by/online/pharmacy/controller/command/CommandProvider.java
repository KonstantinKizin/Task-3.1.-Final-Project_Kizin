package by.online.pharmacy.controller.command;

import by.online.pharmacy.controller.exception.ControllerException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import static by.online.pharmacy.service.impl.PropertyLoader.getConstant;
import static by.online.pharmacy.entity.constant.PropertyEnum.WebProperty;

public class CommandProvider implements Command {

    private Map<String , Command> commandMap = new HashMap<>();;

    @Override
    public CommandReturnObject execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {

        String commandName = request.getParameter(getConstant(WebProperty.HIDDEN_PARAMETER.name()));
        Command command = commandMap.get(commandName);
        return command.execute(request,response);
    }

    public Map<String, Command> getCommandMap() {
        return commandMap;
    }




}
