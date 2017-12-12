package by.online.pharmacy.service.validator.impl;

import by.online.pharmacy.service.validator.Validator;

public class ValidatorImpl implements Validator {


    @Override
    public boolean loginValidate(String email, String password) {
        if(email != null && password != null) {
            if (email.isEmpty() || password.isEmpty()) {
                return false;
            }
        }
        return true;
    }


}
