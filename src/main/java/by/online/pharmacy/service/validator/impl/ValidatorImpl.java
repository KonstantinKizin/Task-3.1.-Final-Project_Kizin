package by.online.pharmacy.service.validator.impl;

import by.online.pharmacy.service.validator.Validator;

import javax.servlet.ServletRequest;

public class ValidatorImpl implements Validator {
    private final String EMAIL_PARAMETER = "email";
    private final String PW_PARAMETER = "password";

    @Override
    public boolean loginValidate(ServletRequest request) {
        String email = request.getParameter(EMAIL_PARAMETER);
        String pw = request.getParameter(PW_PARAMETER);
        if(email != null && pw != null) {
            if (email.isEmpty() || pw.isEmpty()) {
                return false;
            }
        }
        return true;
    }


}
