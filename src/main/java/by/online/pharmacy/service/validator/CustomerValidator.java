package by.online.pharmacy.service.validator;

import by.online.pharmacy.entity.Customer;

public interface CustomerValidator {

    boolean loginValidate(String email , String password);
    boolean registrationValidate(Customer customer);

}
