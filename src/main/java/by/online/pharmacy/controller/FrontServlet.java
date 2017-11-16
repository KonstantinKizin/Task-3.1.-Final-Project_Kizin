package by.online.pharmacy.controller;

import by.online.pharmacy.controller.command.Command;
import by.online.pharmacy.controller.command.ControllerCommandProvider;
import by.online.pharmacy.controller.exception.ControllerException;
import by.online.pharmacy.service.CommandService;
import by.online.pharmacy.service.exception.ServiceException;
import by.online.pharmacy.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "FrontServlet")
public class FrontServlet extends HttpServlet {

    private final ServiceFactory factory = ServiceFactory.getInstance();
    private final CommandService commandService = factory.getCommandService();
    private Command producer = new ControllerCommandProvider();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            producer.execute(request,response);
        } catch (ControllerException e) {
            System.out.println(e);
        }
    }

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            Map<String , Command> commandMap = commandService.getCommandMap();
            ((ControllerCommandProvider) producer).getCommandMap().putAll(commandMap);
        } catch (ServiceException e) {
            throw new RuntimeException(new ControllerException(e));
        }

    }
}
