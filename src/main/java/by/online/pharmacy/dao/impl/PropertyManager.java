package by.online.pharmacy.dao.impl;

import java.util.ResourceBundle;

public class PropertyManager {
    private final static ResourceBundle resource = ResourceBundle.getBundle("config");
    public static String getProperty(String key){
        return resource.getString(key);
    }
}