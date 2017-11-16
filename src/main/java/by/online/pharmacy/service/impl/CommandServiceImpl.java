package by.online.pharmacy.service.impl;
import by.online.pharmacy.controller.command.Command;
import by.online.pharmacy.service.CommandService;
import by.online.pharmacy.service.exception.ServiceException;


import java.util.Map;

public class CommandServiceImpl implements CommandService {
    private final CommandMapCreator mapCreator = new CommandMapCreator();

    @Override
    public Map<String, Command> getCommandMap() throws ServiceException {
        return mapCreator.buildCommandMap();
    }
}
