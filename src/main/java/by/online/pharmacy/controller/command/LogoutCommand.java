package by.online.pharmacy.controller.command;

import by.online.pharmacy.controller.exception.ControllerException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutCommand implements Command {

    private final String MAIN_PAGE = "/index.jsp";
    private CommandReturn commandReturn = new CommandReturn();

    @Override
    public CommandReturn  execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException, IOException {

        request.getSession().invalidate();
        commandReturn.setPage(MAIN_PAGE);
        commandReturn.setRequest(request);
        commandReturn.setResponse(response);
        return commandReturn;
    }
}
