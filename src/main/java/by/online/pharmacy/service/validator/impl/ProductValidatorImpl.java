package by.online.pharmacy.service.validator.impl;

import by.online.pharmacy.controller.constant.ControllerConstant;
import by.online.pharmacy.entity.ValidationError;
import by.online.pharmacy.service.validator.ProductValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class ProductValidatorImpl extends AbstractValidator implements ProductValidator<HttpServletRequest> {


    @Override
    public List<ValidationError> validate(HttpServletRequest request) {

        List<ValidationError> errors = new ArrayList<>();

        if(!checkForNumber(request.getParameter(ControllerConstant.ProductProperty.PRICE_PARAMETER))){
            errors.add(new ValidationError("Price must be a number", ControllerConstant.ProductProperty.PRICE_PARAMETER));
        }

        if(!checkForNumber(request.getParameter(ControllerConstant.ProductProperty.COUNT_PARAMETER))){
            errors.add(new ValidationError("Count must be a number", ControllerConstant.ProductProperty.COUNT_PARAMETER));
        }
        return errors;
    }


    private boolean checkForNumber(String number){
        if(this.isEmptyOrNull(number)){
            return false;
        }else {
            return this.isDigitsOnly(number);
        }
    }

}
