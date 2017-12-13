package by.online.pharmacy.dao.impl;

import by.online.pharmacy.dao.XmlDAO;
import by.online.pharmacy.dao.exception.DAOException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;


public class XmlDAOImpl implements XmlDAO {

    private final String XML_SETTING_FILE_NAME = "Command.cfg.xml";


    public XmlDAOImpl(){

    }

    @Override
    public Document getDocument() throws DAOException {
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


}
