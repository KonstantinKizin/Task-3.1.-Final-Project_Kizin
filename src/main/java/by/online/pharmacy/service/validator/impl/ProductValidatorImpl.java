package by.online.pharmacy.service.validator.impl;

import by.online.pharmacy.entity.Product;
import by.online.pharmacy.service.validator.ProductValidator;

import javax.servlet.http.HttpServletRequest;

public class ProductValidatorImpl implements ProductValidator {
    @Override
    public boolean validate(Product product) {

        return false;


    }

    @Override
    public boolean parametersValidate(HttpServletRequest request) {
        return false;
    }

    private boolean checkStringForEmptyAndNull(String parameter){

        return !parameter.isEmpty() && parameter != null;
    }

    private boolean checkFloatForValidPrice(float price){

        try{
            if(price < 0){
                return false;
            } else {return true;}


        }catch (NumberFormatException e){
            return false;
        }



    }
}
