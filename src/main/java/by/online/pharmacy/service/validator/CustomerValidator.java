package by.online.pharmacy.service.validator;

import by.online.pharmacy.entity.model.Customer;

public interface CustomerValidator {

    boolean loginValidate(String email , String password);
    boolean registrationValidate(Customer customer);

}
