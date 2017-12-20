package by.online.pharmacy.controller.command;

import by.online.pharmacy.controller.exception.ControllerException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.apache.log4j.Logger;

 public class SwitchLanguageCommand implements Command {

     private final static Logger logger = Logger.getLogger(SwitchLanguageCommand.class);

    private final static String REQUEST_PAGE_PARAMETER = "current_page";

    private final static String LANGUAGE_PARAMETER = "language";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {

        try {
            String page = (String) request.getSession().getAttribute(REQUEST_PAGE_PARAMETER);
            String language = (String) request.getParameter(LANGUAGE_PARAMETER);
            request.getSession().setAttribute(LANGUAGE_PARAMETER, language);
            response.sendRedirect(page);
        } catch (IOException e) {
            logger.error("Exception from SwitchLanguageCommand",e);
            throw new ControllerException(e);
        }
    }
}
