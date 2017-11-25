package by.online.pharmacy.controller.command;

import by.online.pharmacy.controller.exception.ControllerException;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    CommandReturnObject execute(HttpServletRequest request ) throws ControllerException;
}
