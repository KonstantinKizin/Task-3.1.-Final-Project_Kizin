package by.online.pharmacy.service.validator.impl;

import by.online.pharmacy.entity.Customer;
import by.online.pharmacy.service.exception.ValidatorException;
import by.online.pharmacy.service.validator.CustomerValidator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerValidatorImpl implements CustomerValidator {

    private static final String EMAIL_REG_EXP =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private static final String PHONE_NUMBER_REG_EXP = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}";;

    private static final String NAME_REG_EXP = "([A-Za-z]+)";

    private static final Pattern PHONE_NUMBER_PATTERN = Pattern.compile(PHONE_NUMBER_REG_EXP);

    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REG_EXP);

    private static final Pattern NAME_PATTER = Pattern.compile(NAME_REG_EXP);

    private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    private final List<String> errors = new ArrayList<>();



    @Override
    public boolean loginValidate(String email, String password) {

        if(email == null || password == null) {
            return false;
        }else if(email.isEmpty() || password.isEmpty()){
            return false;
        }else {
        Matcher matcher = EMAIL_PATTERN .matcher(email);
        return matcher.find();
        }
    }



    @Override
    public List<String> registrationValidate(Customer customer) {

        if(!this.errors.isEmpty()){
            this.errors.clear();
        }

        checkRegistrationParameter(customer.getName(),
                "CUSTOMER_NAME_ERROR",
                NAME_PATTER
                );

        checkRegistrationParameter(customer.getSureName(),
                "CUSTOMER_SURENAME_ERROR",
                NAME_PATTER

                );


        checkRegistrationParameter(customer.getPhoneNumber(),
                "CUSTOMER_PHONE_ERROR",
                PHONE_NUMBER_PATTERN
                );

        checkRegistrationParameter(customer.getEmail(),
                "CUSTOMER_EMAIL_ERROR",
                EMAIL_PATTERN
                );

       checkBirthDate(customer.getDateOfBirth());

        return this.errors;
    }




    private boolean checkForEmptyAndNull(String parameter){
        if(parameter.isEmpty() || parameter == null){
            return false;
        }else {
            return true;
        }
    }




    private void checkRegistrationParameter(String parameter,
                                            String parameterName,
                                            Pattern pattern){

        if(!checkForEmptyAndNull(parameter)){
            this.errors.add(parameterName);
        }else {
            Matcher matcher = pattern .matcher(parameter);
            if(!matcher.find()){
                this.errors.add(parameterName);
            }
        }
    }



    private void checkBirthDate(String formatedDate){

        if(!checkForEmptyAndNull(formatedDate)){
            this.errors.add("CUSTOMER_BIRTH_DATE_ERROR");
        }else{
            Date currentDate = new Date();
            try {
                Date registrationDate = formatter.parse(
                        formatedDate
                );
                if(registrationDate.after(currentDate)){
                    this.errors.add("CUSTOMER_BIRTH_DATE_ERROR");
                }

                currentDate = null;
                registrationDate = null;
            } catch (ParseException e) {
                throw new ValidatorException(e);
            }

        }
    }




}
