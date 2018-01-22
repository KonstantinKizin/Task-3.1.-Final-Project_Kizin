package by.online.pharmacy.controller.command;

import by.online.pharmacy.controller.exception.ControllerException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static by.online.pharmacy.controller.constant.ControllerConstant.WebProperty;

public class LogoutCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        request.getSession().invalidate();
        try {
            response.sendRedirect(WebProperty.PAGE_MAIN);
        } catch (IOException e) {
            throw new ControllerException("exception from Logout Command ",e);
        }
    }
}
