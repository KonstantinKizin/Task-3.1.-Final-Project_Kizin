package by.online.pharmacy.controller;

import by.online.pharmacy.controller.command.Command;
import by.online.pharmacy.controller.command.CommandMapCreator;
import by.online.pharmacy.controller.command.CommandProvider;
import by.online.pharmacy.controller.exception.ControllerException;
import by.online.pharmacy.controller.exception.InitializeServletException;
import by.online.pharmacy.service.exception.ServiceException;
import by.online.pharmacy.service.factory.ServiceFactory;
import by.online.pharmacy.util.ProductLoader;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import static by.online.pharmacy.controller.constant.ControllerConstant.WebProperty;

public class FrontController extends HttpServlet {

    private final ServiceFactory factory = ServiceFactory.getInstance();
    private final CommandMapCreator commandMapCreator = new CommandMapCreator();
    private final Command producer = new CommandProvider();
    private final static Logger logger = Logger.getLogger(FrontController.class);
    private final static ProductLoader productLoader = ProductLoader.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            request.setCharacterEncoding(WebProperty.CHARACTER_ENCODING);
            response.setCharacterEncoding(WebProperty.CHARACTER_ENCODING);
            String commandName = request.getParameter(WebProperty.HIDDEN_PARAMETER);
            Command command = ((CommandProvider)producer).getCommandMap().get(commandName);
            command.execute(request,response);

        } catch (ControllerException e) {
            logger.error("Exception from FrontController",e);
            response.sendRedirect(WebProperty.ERROR_PAGE);
        }
    }


    @Override
    public void init() throws ServletException {
        super.init();
        try {
            Map<String , Command> commandMap = commandMapCreator.getCommandMap();
            ((CommandProvider) producer).getCommandMap().putAll(commandMap);
            this.getServletContext().setAttribute("productList",productLoader.getProductList());
        } catch (ServiceException e) {
            logger.error("Exception in init method",e);
            throw new InitializeServletException();
        }

    }
}
