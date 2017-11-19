package by.online.pharmacy.controller.command;

import by.online.pharmacy.controller.exception.ControllerException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RedirectMainPageCommand implements Command {

    private final String INDEX_JSP = "/index.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {

        try {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
            requestDispatcher.forward(request,response);
            response.sendRedirect(INDEX_JSP);
        } catch (IOException e) {
            throw new ControllerException(e);
        } catch (ServletException e) {
            throw new ControllerException(e);
        }
    }
}
