package by.online.pharmacy.service.validator;


import by.online.pharmacy.entity.Product;

import java.util.Map;

public interface ProductValidator extends Validator<Product> {

    boolean parametersValidate(Map<String,String> parameters);

}


