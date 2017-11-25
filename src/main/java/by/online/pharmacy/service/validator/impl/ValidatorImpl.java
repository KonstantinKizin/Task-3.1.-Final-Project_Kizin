package by.online.pharmacy.service.validator.impl;

import by.online.pharmacy.service.validator.Validator;

import javax.servlet.ServletRequest;
import static by.online.pharmacy.service.impl.PropertyLoader.getConstant;
import static by.online.pharmacy.entity.constant.PropertyEnum.RegistrationProperty;

public class ValidatorImpl implements Validator {


    @Override
    public boolean loginValidate(ServletRequest request) {
        String email = request.getParameter(getConstant(RegistrationProperty.EMAIL_PARAMETER.name()));
        String pw = request.getParameter(getConstant(RegistrationProperty.PW_PARAMETER.name()));
        if(email != null && pw != null) {
            if (email.isEmpty() || pw.isEmpty()) {
                return false;
            }
        }
        return true;
    }


}
