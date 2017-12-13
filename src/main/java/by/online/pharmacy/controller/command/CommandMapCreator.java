package by.online.pharmacy.controller.command;


import by.online.pharmacy.dao.exception.DAOException;
import by.online.pharmacy.service.exception.ServiceException;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

public class CommandMapCreator {

    private final String XML_SETTING_FILE_NAME = "Command.cfg.xml";
    private final static Logger logger = Logger.getLogger(CommandMapCreator.class);
    private final List<String > COMMAND_NAMES_LIST = new ArrayList<>();
    private final List<String > CLASS_NAMES_LIST = new ArrayList<>();
    private final String COMMAND_NAME_TAG = "command-name";
    private final String COMMAND_CLASS_NAME_TAG = "command-class";


    private Document getDocument() throws DAOException {
        try{
            File file = new File(this.getAbsoluteFilePath(XML_SETTING_FILE_NAME));
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document document = builder.parse(file);
            return document;
        }catch (ParserConfigurationException | IOException | SAXException e ){
            throw new DAOException("Exception in build Xml command config parse",e);
        }


    }


    private String getAbsoluteFilePath(String fileName){
        ClassLoader classLoader = getClass().getClassLoader();
        String path = new String(classLoader.getResource(fileName).getPath());
        return path;
    }

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

    private Map<String , Command> buildCommandMap() throws ServiceException {
        Map<String , Command> commandMap = new HashMap<>();
        try {
            Document xmlDocument = this.getDocument();
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

    private Map<String , String> getCommandMap(NodeList nodeList){

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
