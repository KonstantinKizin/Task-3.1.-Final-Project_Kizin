package by.online.pharmacy.service;

import by.online.pharmacy.controller.command.Command;
import by.online.pharmacy.service.exception.ServiceException;

import java.util.Map;

public interface CommandService {

    Map<String ,Command> getCommandMap() throws ServiceException;;
}
