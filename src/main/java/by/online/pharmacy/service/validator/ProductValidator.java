package by.online.pharmacy.service.validator;

import by.online.pharmacy.entity.Product;

import javax.servlet.http.HttpServletRequest;

public interface ProductValidator {

    boolean validate(Product product);

    boolean parametersValidate(HttpServletRequest request);

}


