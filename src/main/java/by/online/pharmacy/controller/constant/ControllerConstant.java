package by.online.pharmacy.controller.constant;

import java.io.File;

public class ControllerConstant {

    public static class  WebProperty{

        public final static String  CUSTOMER_PAGE = "/customer_autho";
        public final static String ADMIN_PAGE = "/admin_autho";
        public final static String ERROR_PAGE = "/error";
        public final static String CUSTOMERS_LIST_PAGE= "/WEB-INF/jsp/admin/customers.jsp";
        public final static String  REGISTRATION_PAGE = "/registration.jsp";
        public final static String MAIN_PAGE = "/index.jsp";
        public final static String HIDDEN_PARAMETER = "hidden";
        public final static String USER_ATTRIBUTE_NAME = "user";
        public final static String ADMIN_ROLE = "admin";
        public final static String CUSTOMER_ROLE = "customer";
        public final static String LANGUAGE_PARAMETER = "language";
        public final static String CUSTOMER_LIST_ATTR_NAME = "customerList";
        public final static String CHARACTER_ENCODING = "utf-8";
        public final static String PAGE = "page";
        public final static String SAVE_PRODUCT_URL="/admin/saveproduct";
        public final static String CUSTOMERS_URL="/admin/customers";


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

    public static class ProductProperty{
        public final static String ENG_DESCRIPTION_PARAMETER = "eng_description";
        public final static String RUS_DESCRIPTION_PARAMETER = "rus_description";
        public final static String ENG_NAME_PARAMETER = "eng_name";
        public final static String RUS_NAME_PARAMETER = "rus_name";
        public final static String ENG_CATEGORY_PARAMETER = "eng_category";
        public final static String RUS_CATEGORY_PARAMETER = "rus_category";
        public final static String ENG_MANUFACTURE_PARAMETER = "eng_manufacture";
        public final static String RUS_MANUFACTURE_PARAMETER = "rus_manufacture";
        public final static String PRESCRIPTION_PARAMETER = "prescription";
        public final static String COUNT_PARAMETER = "count";
        public final static String PRICE_PARAMETER = "price";
        public final static String DOSAGE_PARAMETER = "dosage";
        public final static String IMAGE_URL_PARAMETER = "image_path";



    }


}
