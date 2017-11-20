package by.online.pharmacy.entity;

import by.online.pharmacy.dao.impl.PropertyManager;

public class test {

    String s = PropertyManager.getProperty("SERVER_PAGE");

    public static void main(String[] args) {
        String s = PropertyManager.getProperty("SERVER_PAGE");
        System.out.println(s);
    }
}
