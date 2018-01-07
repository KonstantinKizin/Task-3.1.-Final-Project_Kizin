package by.online.pharmacy.service.validator;

import by.online.pharmacy.entity.Customer;


import java.util.List;

public interface CustomerValidator {

    boolean loginValidate(String email , String password);

    List<String> registrationValidate(Customer customer);


}
