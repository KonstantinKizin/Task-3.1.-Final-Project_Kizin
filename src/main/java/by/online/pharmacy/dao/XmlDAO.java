package by.online.pharmacy.dao;

import by.online.pharmacy.dao.exception.DAOException;
import org.w3c.dom.Document;

public interface XmlDAO {
    Document getDocument() throws DAOException;
}
