package by.online.pharmacy.controller.command;

import by.online.pharmacy.controller.exception.ControllerException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static by.online.pharmacy.dao.impl.PropertyManager.getProperty;

public class LogoutCommand implements Command {

   ;
    private CommandReturnObject commandReturn = new CommandReturnObject();

    @Override
    public CommandReturnObject execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {

        request.getSession().invalidate();
        commandReturn.setPage(getProperty("MAIN_PAGE"));
        commandReturn.setRequest(request);
        commandReturn.setResponse(response);
        return commandReturn;
    }
}
