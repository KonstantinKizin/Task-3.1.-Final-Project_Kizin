package by.online.pharmacy.service.impl;

import by.online.pharmacy.controller.command.Command;
import by.online.pharmacy.dao.CommandXMLDAO;
import by.online.pharmacy.dao.exception.DAOException;
import by.online.pharmacy.dao.factory.DAOFactory;
import by.online.pharmacy.service.exception.ServiceException;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CommandMapCreator {// шикарное название класса
    // человеку, первый раз читающему твой код, так все понятно будет, зачет тебе все это

    private final DAOFactory factory = DAOFactory.getInstance();
    private final CommandXMLDAO xmlDao = factory.getCommandXMLDao();
    private final static Logger logger = Logger.getLogger(CommandMapCreator.class);

    public CommandMapCreator()  {

    }
    private Command buildCommand(String commandClassName) throws  ServiceException {

        try {
            Class clazz = Class.forName(commandClassName);
            Command command =  (Command) clazz.newInstance();
            return command;

        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            throw new ServiceException("Creating command from config xml file",e);
        }
    }


    public Map<String , Command> buildCommandMap() throws ServiceException {

        Map<String , Command> commandMap = new HashMap<>();
        try {
            Document xmlDocument = xmlDao.getDocument();
            Map<String , String> map = xmlDao.getCommandMap(xmlDocument.getChildNodes());
            Set<String> keySet = map.keySet();
            for (String key : keySet) {
                String commandClassName = map.get(key);
                Command command = this.buildCommand(commandClassName);
                commandMap.put(key, command);
            }
            return commandMap;
        }catch (ServiceException e){
            logger.error("Build command map  Exception fom CommandMapCreatorFile",e);
            throw e;
        } catch (DAOException e) {
            logger.error("From build Command map method",e);
            throw new ServiceException(e);
        }

    }

}
