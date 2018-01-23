package by.online.pharmacy.service.validator.impl;

import by.online.pharmacy.controller.constant.ControllerConstant;
import by.online.pharmacy.entity.ValidationError;
import by.online.pharmacy.service.validator.ProductValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductValidatorImpl extends AbstractValidator implements ProductValidator {


    public List<ValidationError> validate(Map<String,String> parameterMap) {

        List<ValidationError> errors = new ArrayList<>();

        if(!checkForNumber(parameterMap.get(ControllerConstant.ProductProperty.PRICE_PARAMETER))){
            errors.add(new ValidationError("Price must be a number", ControllerConstant.ProductProperty.PRICE_PARAMETER));
        }

        if(!checkForNumber(parameterMap.get(ControllerConstant.ProductProperty.COUNT_PARAMETER))){
            errors.add(new ValidationError("Count must be a number", ControllerConstant.ProductProperty.COUNT_PARAMETER));
        }

        if(this.isEmptyOrNull(parameterMap.get(ControllerConstant.ProductProperty.DESCRIPTION_PARAMETER))){
            errors.add(new ValidationError("Fill description of product", ControllerConstant.ProductProperty.DESCRIPTION_PARAMETER));
        }

        if(this.isEmptyOrNull(parameterMap.get(ControllerConstant.ProductProperty.NAME_PARAMETER))){
            errors.add(new ValidationError("Fill name of product", ControllerConstant.ProductProperty.NAME_PARAMETER));
        }

        return errors;
    }


    private boolean checkForNumber(String number){
        if(this.isEmptyOrNull(number)){
            return false;
        }else{
            return this.isPositiveDigitsOnly(number);
        }

    }



}
