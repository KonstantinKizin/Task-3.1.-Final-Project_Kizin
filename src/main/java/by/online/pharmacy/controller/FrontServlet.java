package by.online.pharmacy.controller;

import by.online.pharmacy.controller.command.Command;
import by.online.pharmacy.controller.command.CommandReturnObject;
import by.online.pharmacy.controller.command.CommandProvider;
import by.online.pharmacy.controller.exception.ControllerException;
import by.online.pharmacy.controller.exception.InitializeServletException;
import by.online.pharmacy.service.CommandService;
import by.online.pharmacy.service.exception.ServiceException;
import by.online.pharmacy.service.factory.ServiceFactory;
import org.apache.log4j.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import static by.online.pharmacy.controller.constant.ControllerConstant.WebProperty;

public class FrontServlet extends HttpServlet {

    private final ServiceFactory factory = ServiceFactory.getInstance();
    private final CommandService commandService = factory.getCommandService();
    private final Command producer = new CommandProvider();
    private final static Logger logger = Logger.getLogger(FrontServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        try {
            request.setCharacterEncoding(WebProperty.CHARACTER_ENCODING);
            CommandReturnObject commandReturn = producer.execute(request);
            HttpServletRequest httpReturnedRequest = commandReturn.getRequest();
            String page = commandReturn.getPage();
            if(!page.equalsIgnoreCase(WebProperty.MAIN_PAGE)){

                RequestDispatcher rd = request.getRequestDispatcher(page);
                rd.forward(httpReturnedRequest,response);

            }else {
                response.sendRedirect(page);
            }
        } catch (ControllerException e) {
            response.sendRedirect(WebProperty.ERROR_PAGE);
            logger.error("Exception from FrontServlet",e);
        }
    }


    @Override
    public void init() throws ServletException {
        super.init();
        try {

            Map<String , Command> commandMap = commandService.getCommandMap();
            ((CommandProvider) producer).getCommandMap().putAll(commandMap);
        } catch (ServiceException e) {
            logger.error("Exception in init method",e);
            throw new InitializeServletException();
        }

    }
}
