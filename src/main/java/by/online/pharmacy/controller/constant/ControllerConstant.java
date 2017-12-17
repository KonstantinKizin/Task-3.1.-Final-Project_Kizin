package by.online.pharmacy.controller.constant;

public class ControllerConstant {

    public static class  WebProperty{

        public final static String  CUSTOMER_PAGE = "/WEB-INF/jsp/customer.jsp";
        public final static String ADMIN_PAGE = "/WEB-INF/jsp/admin.jsp";
        public final static String ERROR_PAGE = "/WEB-INF/jsp/error.jsp";
        public final static String CUSTOMERS_LIST_PAGE= "/WEB-INF/jsp/admin/customers.jsp";
        public final static String  REGISTRATION_PAGE = "/registration.jsp";
        public final static String MAIN_PAGE = "/index.jsp";
        public final static String HIDDEN_PARAMETER = "hidden";
        public final static String USER_ATTRIBUTE_NAME = "user";
        public final static String ADMIN_ROLE = "admin";
        public final static String CUSTOMER_ROLE = "customer";
        public final static String CUSTOMER_LIST_ATTR_NAME = "customerList";
        public final static String CHARACTER_ENCODING = "utf-8";
        public final static String SING_IN_ERROR_ATTR_NAME = "findOrValidationError";
        public final static String SING_IN_ERROR_MESSAGE = "Wrong Email or password";
        public final static String PAGE = "page";
        public final static String REDIRECT_URL = "/frontController?hidden=redirect";
        public final static String SAVE_PRODUCT_PAGE = "/WEB-INF/jsp/admin/saveproduct.jsp";
    }

    public static class RegistrationProperty{

        public final static String NAME_PARAMETER = "name";
        public final static String SURE_NAME_PARAMETER = "sureName";
        public final static String LOGIN_PARAMETER = "login";
        public final static String PW_PARAMETER = "password";
        public final static String EMAIL_PARAMETER = "email";
        public final static String PHONE_PARAMETER = "phone";
        public final static String BIRTH_DATE_PARAMETER = "birthDate";
        public final static String GENDER_PARAMETER = "gender";
    }


}
