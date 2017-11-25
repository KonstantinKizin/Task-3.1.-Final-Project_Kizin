package by.online.pharmacy.entity.constant;

public class PropertyEnum {

    public static enum WebProperty{
        CUSTOMER_PAGE,
        ADMIN_PAGE,
        ERROR_PAGE,
        CUSTOMERS_LIST_PAGE,
        MAIN_PAGE,
        HIDDEN_PARAMETER,
        SING_IN_ERROR_ATTR_NAME,
        SING_IN_ERROR_MESSAGE,
        COMMAND_CONFIG_FILE_NAME,
        USER_ATTRIBUTE_NAME,
        ADMIN_ROLE,
        CUSTOMER_ROLE,
        CUSTOMER_LIST_ATTR_NAME,
    }

    public static enum  DateBaseProperty{
        DB_DRIVER_NAME,
        DB_LOGIN,
        DB_PASSWORD,
        DB_URL,
    }

    public static enum RegistrationProperty{
        NAME_PARAMETER,
        SURE_NAME_PARAMETER,
        LOGIN_PARAMETER,
        PW_PARAMETER,
        EMAIL_PARAMETER,
        PHONE_PARAMETER,
        BIRTH_DATE_PARAMETER,
        GENDER_PARAMETER,
    }

}
