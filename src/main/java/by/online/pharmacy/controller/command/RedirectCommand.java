package by.online.pharmacy.controller.command;

import by.online.pharmacy.controller.exception.ControllerException;
import org.apache.log4j.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import static by.online.pharmacy.controller.constant.ControllerConstant.WebProperty;

public class RedirectCommand implements Command {

    private final static Logger logger = Logger.getLogger(RedirectCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {

        String page = (String)request.getSession().getAttribute(WebProperty.PAGE);
        try {
            request.getRequestDispatcher(page).forward(request,response);
        } catch (ServletException |IOException e) {
            logger.error("Exception in redirect command ",e);
            throw new ControllerException(e);
        }

    }
}
