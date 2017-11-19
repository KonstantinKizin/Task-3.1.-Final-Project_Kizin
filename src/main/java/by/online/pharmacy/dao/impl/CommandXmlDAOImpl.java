package by.online.pharmacy.dao.impl;

import by.online.pharmacy.dao.CommandXMLDAO;
import by.online.pharmacy.dao.exception.DAOException;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandXmlDAOImpl implements CommandXMLDAO {

    private final List<String > COMMAND_NAMES_LIST = new ArrayList<>();
    private final List<String > CLASS_NAMES_LIST = new ArrayList<>();
    private final String COMMAND_NAME_TAG = "command-name";
    private final String COMMAND_CLASS_NAME_TAG = "command-class";
    private final String XML_SETTING_FILE_NAME = "Command.cfg.xml";


    public CommandXmlDAOImpl(){

    }

    @Override
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

    @Override
    public Document getDocument() throws DAOException {
        try{
            File file = new File(this.getAbsoluteFilePath(XML_SETTING_FILE_NAME));
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            final Document document = builder.parse(file);
            return document;
        }catch (ParserConfigurationException | IOException | SAXException e ){
            throw new DAOException(e);
        }


    }


    private String getAbsoluteFilePath(String fileName){
        ClassLoader classLoader = getClass().getClassLoader();
        String path = new String(classLoader.getResource(fileName).getPath());
        return path;
    }


}
