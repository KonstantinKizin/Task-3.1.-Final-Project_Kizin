package by.online.pharmacy.service.impl;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

public  class PropertyLoader {

    private final static String DB_PROPERTY_NAME = "JDBCconfig";// вот только такие провепти в такими именами в сервисах и грузить
    // включай мышление!!!!
    private final static String WEB_PROPERTY_NAME = "WebConfig";

    private static final PropertyLoader instance = new PropertyLoader();

    private static  ResourceBundle resource;

    private final static  Map<String , String> propertyMap = new HashMap<>();

    static {
        resource = ResourceBundle.getBundle(DB_PROPERTY_NAME);
        addProperty(resource);
        resource = ResourceBundle.getBundle(WEB_PROPERTY_NAME);
        addProperty(resource);
    }



    private PropertyLoader(){

    }

    private static void addProperty(ResourceBundle resource){
        Set<String > keys = resource.keySet();
        for(String tmp : keys){
            propertyMap.put(tmp , resource.getString(tmp));
        }
    }

    public static String getConstant(String key){
        return propertyMap.get(key);
    }

    public static Map<String, String> getPropertyMap() {
        return propertyMap;
    }

    public static PropertyLoader getInstance(){
        return instance;
    }

}

