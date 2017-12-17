package by.online.pharmacy.service.validator.impl;

import by.online.pharmacy.entity.Customer;
import by.online.pharmacy.service.validator.CustomerValidator;

public class CustomerValidatorImpl implements CustomerValidator {


    @Override
    public boolean loginValidate(String email, String password) {

        if(email == null || password == null) {
            return false;
        }else {
            if(email.isEmpty() || password.isEmpty()){
                return false;
            }else {
                return true;
            }
        }
    }

    @Override
    public boolean registrationValidate(Customer customer) {

        if(!checkForEmptyAndNull(customer.getName())){
            return false;
        }else if(!checkForEmptyAndNull(customer.getSureName())){
            return false;
        }else if(!checkForEmptyAndNull(customer.getPhoneNumber())){
            return false;
        }else if(!checkForEmptyAndNull(customer.getRole())){
            return false;
        }else if(!checkForEmptyAndNull(customer.getLogin())){
            return false;
        }else if(!checkForEmptyAndNull(customer.getPassword())){
            return false;
        }else if(!checkForEmptyAndNull(customer.getDateOfBirth())){
            return false;
        }
        return true;
    }


    private boolean checkForEmptyAndNull(String parameter){
        if(parameter.isEmpty() || parameter == null){
            return false;
        }else {
            return true;
        }
    }


}
