package by.online.pharmacy.controller.command;

import by.online.pharmacy.controller.exception.ControllerException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.apache.log4j.Logger;

public class SwitchLanguageCommand implements Command {

    private final static String REQUEST_PAGE_PARAMETER = "page";
    private final static String REQUEST_LANGUAGE_PARAMETER = "language";
    private final static Logger logger = Logger.getLogger(SwitchLanguageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        Object attrObject = request.getSession().getAttribute(REQUEST_PAGE_PARAMETER);
        String page = (String)attrObject;
        String lang = request.getParameter(REQUEST_LANGUAGE_PARAMETER);
        request.setAttribute(REQUEST_LANGUAGE_PARAMETER,lang);
        try {
            request.getRequestDispatcher(page).forward(request,response);
        } catch (ServletException | IOException e) {
            logger.error("Exception from SwitchLanguageCommand",e);
            throw new ControllerException(e);
        }
    }
}
