package by.online.pharmacy.controller.command;

import by.online.pharmacy.controller.exception.ControllerException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
    void execute(HttpServletRequest request , HttpServletResponse response) throws ControllerException;
}
