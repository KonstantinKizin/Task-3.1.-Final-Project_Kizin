package by.online.pharmacy.controller.constant;


public class ControllerConstant {


    public static class  WebProperty{

        public final static String PAGE_CUSTOMER = "/account";
        public final static String PAGE_ADMIN = "/admin/account";
        public final static String PAGE_ERROR= "/error";
        public final static String PAGE_CUSTOMER_LIST= "/admin/customers";
        public final static String PAGE_REGISTRATION = "/registration";
        public final static String PAGE_PRODUCT_SETTING = "/admin/product/setting?id=";
        public final static String PAGE_MAIN = "/home";
        public final static String PAGE_NOT_FOUND = "/WEB-INF/PageNotFound.jsp";
        public final static String PAGE_LOGIN = "/login";
        public final static String PAGE_PRODUCT = "/product";



        public final static String HIDDEN_PARAMETER = "hidden";
        public final static String USER_ATTRIBUTE_NAME = "user";
        public final static String USER_ATTRIBUTE_ROLE = "role";
        public final static String ADMIN_ROLE = "admin";
        public final static String CUSTOMER_ROLE = "customer";
        public final static String GUEST_ROLE = "guest";
        public final static String LANGUAGE_PARAMETER = "language";
        public final static String CUSTOMER_LIST_ATTR_NAME = "customerList";
        public final static String PRODUCT_LIST_ATTR_NAME = "productList";
        public final static String CHARACTER_ENCODING = "utf-8";
        public final static String PAGE = "page";
        public final static String SING_IN_ERROR_PARAMETER = "sing_in_error";
        public final static String SING_IN_ERROR_VALUE = "error_value";
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

        public final static String PRICE_PARAMETER = "product_price";
        public final static String COUNT_PARAMETER = "product_count";
        public final static String PRESCRIPTION_PARAMETER = "product_prescription";
        public final static String NAME_PARAMETER = "product_name";
        public final static String MANUFACTURE_PARAMETER = "product_manufacture";
        public final static String DESCRIPTION_PARAMETER = "product_description";
        public final static String CATEGORY_PARAMETER = "product_category";
        public final static String LANGUAGE_PARAMETER = "product_language";
        public final static String ID_PARAMETER = "product_id";
        public final static String CURRENT_PRODUCT_PARAMETER = "current_product";
        public final static String IMAGE_PARAMETER = "product_image";

    }


}
