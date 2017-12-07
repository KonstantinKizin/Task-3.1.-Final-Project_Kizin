package by.online.pharmacy.controller.command;

import by.online.pharmacy.controller.exception.ControllerException;

import javax.servlet.http.HttpServletRequest;

public class SwitchLanguageCommand implements Command {

    private final CommandReturnObject commandReturn = new CommandReturnObject();
    private final static String REQUEST_PAGE_PARAMETER = "page";
    private final static String REQUEST_LANGUAGE_PARAMETER = "language";

    @Override
    public CommandReturnObject execute(HttpServletRequest request) throws ControllerException {

        Object attrObject = request.getSession().getAttribute(REQUEST_PAGE_PARAMETER);
        String page = (String)attrObject;
        if(page.isEmpty()){
           page = request.getParameter(REQUEST_PAGE_PARAMETER);
        }
        String lang = request.getParameter(REQUEST_LANGUAGE_PARAMETER);
        request.setAttribute(REQUEST_LANGUAGE_PARAMETER,lang);
        commandReturn.setRequest(request);
        commandReturn.setPage(page);
        return commandReturn;
    }
}
