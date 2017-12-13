package by.online.pharmacy.service.impl;
import by.online.pharmacy.controller.command.Command;
import by.online.pharmacy.dao.XmlDAO;
import by.online.pharmacy.dao.exception.DAOException;
import by.online.pharmacy.dao.factory.DAOFactory;
import by.online.pharmacy.service.CommandService;
import by.online.pharmacy.service.exception.ServiceException;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


import java.util.*;

public class CommandServiceImpl implements CommandService {
    private final DAOFactory factory = DAOFactory.getInstance();
    private final XmlDAO xmlDao = factory.getCommandXMLDao();
    private final static Logger logger = Logger.getLogger(CommandServiceImpl.class);
    private final List<String > COMMAND_NAMES_LIST = new ArrayList<>();
    private final List<String > CLASS_NAMES_LIST = new ArrayList<>();
    private final String COMMAND_NAME_TAG = "command-name";
    private final String COMMAND_CLASS_NAME_TAG = "command-class";



    @Override
    public Map<String, Command> getCommandMap() throws ServiceException {
        return buildCommandMap();
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
            Map<String , String> map = this.getCommandMap(xmlDocument.getChildNodes());
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

    public Map<String , String> getCommandMap(NodeList nodeList){

        Map<String,String> commandMap = new HashMap<>();

        List<String> keys = this.buildKeysList(nodeList);

        List<String> commandNames = this.buildCommandNameList(nodeList);

        for(int i = 0; i < keys.size(); i++){
            commandMap.put(keys.get(i),commandNames.get(i));
        }
        return commandMap;
    }

    private List<String> buildKeysList(NodeList nodeList){
        return buildTextContentList(nodeList,COMMAND_NAME_TAG, COMMAND_NAMES_LIST);

    }

    private List<String> buildCommandNameList(NodeList nodeList){
        return buildTextContentList(nodeList,COMMAND_CLASS_NAME_TAG,CLASS_NAMES_LIST);
    }




    private List<String> buildTextContentList(NodeList nodeList , String tagName,List<String> list){

        for(int i = 0 ; i < nodeList.getLength();i++){

            Node node = nodeList.item(i);

            if(node.getNodeType() == Node.ELEMENT_NODE){

                Element element = (Element)node;

                if(element.getTagName().equalsIgnoreCase(tagName)){

                    list.add(element.getTextContent());
                }
                buildTextContentList(node.getChildNodes(),tagName,list);
            }
        }

        return list;

    }


}
