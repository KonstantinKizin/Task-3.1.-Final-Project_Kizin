package by.online.pharmacy.dao;

import by.online.pharmacy.dao.exception.DAOException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.util.Map;

public interface XmlDAO {
    Document getDocument() throws DAOException;
    //Map<String , String> getCommandMap(NodeList nodeList);
}
