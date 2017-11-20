package by.online.pharmacy.controller;

import by.online.pharmacy.controller.command.Command;
import by.online.pharmacy.controller.command.CommandReturnObject;
import by.online.pharmacy.controller.command.ControllerCommandProvider;
import by.online.pharmacy.controller.exception.ControllerException;
import by.online.pharmacy.service.CommandService;
import by.online.pharmacy.service.exception.ServiceException;
import by.online.pharmacy.service.factory.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;
import static by.online.pharmacy.dao.impl.PropertyManager.getProperty;


@WebServlet(name = "FrontServlet")
public class FrontServlet extends HttpServlet {

    private final ServiceFactory factory = ServiceFactory.getInstance();
    private final CommandService commandService = factory.getCommandService();
    private Command producer = new ControllerCommandProvider();
    private final static Logger logger = Logger.getLogger(FrontServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            CommandReturnObject commandReturn = producer.execute(request,response);

            String page = commandReturn.getPage();
            HttpServletRequest httpRequest = commandReturn.getRequest();
            HttpServletResponse httpResponse = commandReturn.getResponse();

            if(commandReturn.getPage() != null){
                RequestDispatcher rd = request.getRequestDispatcher(commandReturn.getPage());
                rd.forward(httpRequest,httpResponse);
            }else {
                page = getProperty("MAIN_PAGE");
                HttpSession session = request.getSession(false);
                session.setAttribute(getProperty("SING_IN_ERROR_ATTR_NAME"), "Wrong Email or password");
                httpResponse.sendRedirect(page);
            }

        } catch (ControllerException e) {
            logger.debug("Exception from FrontServlet",e);
        }
    }


    @Override
    public void init() throws ServletException {
        super.init();
        try {
            Map<String , Command> commandMap = commandService.getCommandMap();
            ((ControllerCommandProvider) producer).getCommandMap().putAll(commandMap);
        } catch (ServiceException e) {
            logger.debug("Exception in init method",e);
            throw new RuntimeException(new ControllerException(e));
        }

    }
}
