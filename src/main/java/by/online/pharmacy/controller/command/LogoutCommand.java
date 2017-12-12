package by.online.pharmacy.controller.command;

import by.online.pharmacy.controller.exception.ControllerException;

import javax.servlet.http.HttpServletRequest;

import static by.online.pharmacy.controller.constant.ControllerConstant.WebProperty;

public class LogoutCommand implements Command {

    private final CommandReturnObject commandReturn = new CommandReturnObject();

    @Override
    public CommandReturnObject execute(HttpServletRequest request) throws ControllerException {

        request.getSession().invalidate();
        commandReturn.setPage(WebProperty.MAIN_PAGE);
        commandReturn.setRequest(request);
        return commandReturn;
    }
}
