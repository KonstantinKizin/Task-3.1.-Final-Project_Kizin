package by.online.pharmacy.controller;
import by.online.pharmacy.controller.command.Command;
import by.online.pharmacy.controller.command.CommandMapCreator;
import by.online.pharmacy.controller.command.CommandProvider;
import by.online.pharmacy.controller.exception.ControllerException;
import by.online.pharmacy.controller.exception.InitializeServletException;
import by.online.pharmacy.service.exception.ServiceException;
import by.online.pharmacy.service.storage.ProductStorage;
import org.apache.log4j.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import static by.online.pharmacy.controller.constant.ControllerConstant.WebProperty;


public class FrontController extends HttpServlet {

    private final CommandMapCreator commandMapCreator = new CommandMapCreator();
    private final static Logger logger = Logger.getLogger(FrontController.class);
    private final ProductStorage productLoader = ProductStorage.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            request.setCharacterEncoding(WebProperty.CHARACTER_ENCODING);
            response.setCharacterEncoding(WebProperty.CHARACTER_ENCODING);
            String commandName = request.getParameter(WebProperty.HIDDEN_PARAMETER);
            if(commandName == null){
                commandName = (String) request.getAttribute(WebProperty.HIDDEN_PARAMETER);
            }
            Command command = CommandProvider.getInstance().getCommandMap().get(commandName);
            command.execute(request,response);
        } catch (ControllerException e) {
            logger.error("Exception from FrontController",e);
            response.sendRedirect(WebProperty.PAGE_ERROR);
        }
    }


    @Override
    public void init() throws ServletException {
        super.init();
        try {
            Map<String , Command> commandMap = commandMapCreator.getCommandMap();
            CommandProvider.getInstance().getCommandMap().putAll(commandMap);
            this.getServletContext().setAttribute(
                    WebProperty.PRODUCT_LIST_ATTR_NAME,
                    productLoader.getProductList()
            );

        } catch (ServiceException e){
            logger.error("Exception in init method",e);
            throw new InitializeServletException();
        }
    }




}
