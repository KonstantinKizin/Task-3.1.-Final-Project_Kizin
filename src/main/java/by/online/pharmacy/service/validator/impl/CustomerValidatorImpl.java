package by.online.pharmacy.service.validator.impl;

import by.online.pharmacy.entity.Customer;
import by.online.pharmacy.entity.ValidationError;
import by.online.pharmacy.service.exception.ValidatorException;
import by.online.pharmacy.service.validator.CustomerValidator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;
import static by.online.pharmacy.controller.constant.ControllerConstant.RegistrationProperty;

public class CustomerValidatorImpl extends AbstractValidator implements CustomerValidator<Customer> {

    private static final String EMAIL_REG_EXP = "([a-zA-Z0-9]+)(@)([a-zA-Z]+)(\\.)([a-zA-Z]){2,3}";

    private static final String PHONE_NUMBER_REG_EXP = "^(\\+?)(\\d){10,12}|^(\\+?)(\\d){3}-(\\d){9}|^(\\+?)(\\d){3}-(\\d){2}-(\\d){7,9}";;

    private static final String NAME_REG_EXP = "([a-zA-Z]+)";

    private static final Pattern PHONE_NUMBER_PATTERN = Pattern.compile(PHONE_NUMBER_REG_EXP);

    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REG_EXP);

    private static final Pattern NAME_PATTER = Pattern.compile(NAME_REG_EXP);

    private static final String CLIENT_RECOMMENDATION = "please, turn on JavaScript";

    private static final String RECOMMENDATION_PARAMETER = "recommendation";

    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    public CustomerValidatorImpl(){

    }

    @Override
    public List<ValidationError> validate(Customer customer) {

        List<ValidationError> errors = new ArrayList<>();

        if(!checkBirthDate(customer.getDateOfBirth())){
            errors.add(new ValidationError("incorrect birth date",RegistrationProperty.BIRTH_DATE_PARAMETER));
        }

        if(!checkName(customer.getSureName())){
            errors.add(new ValidationError("incorrect second name",RegistrationProperty.SURE_NAME_PARAMETER));
        }

        if(!checkName(customer.getName())){
            errors.add(new ValidationError("incorrect  name",RegistrationProperty.NAME_PARAMETER));
        }

        if(!checkEmail(customer.getEmail())){
            errors.add(new ValidationError("incorrect email",RegistrationProperty.EMAIL_PARAMETER));
        }

        if(!checkLogin(customer.getLogin())){
            errors.add(new ValidationError("incorrect login",RegistrationProperty.LOGIN_PARAMETER));
        }

        if(!checkPhoneNumber(customer.getPhoneNumber())){
            errors.add(new ValidationError("incorrect phone number",RegistrationProperty.PHONE_PARAMETER));
        }

        if(!checkPassword(customer.getPassword())){
            errors.add(new ValidationError("incorrect password",RegistrationProperty.PW_PARAMETER));
        }

        if(!errors.isEmpty()){
            errors.add(new ValidationError(CLIENT_RECOMMENDATION,RECOMMENDATION_PARAMETER));
        }

        return errors;
    }



    private boolean checkName(String name){

        if(this.isEmptyOrNull(name)){
            return false;
        }else {
            return this.matchesPattern(NAME_PATTER,name);
        }

    }

    private boolean checkPhoneNumber(String phone){
        if(this.isEmptyOrNull(phone)){
            return false;
        }else {
            return this.matchesPattern(PHONE_NUMBER_PATTERN,phone);
        }
    }

    private boolean checkEmail(String email){

        if(this.isEmptyOrNull(email)){
            return false;
        }else {
           return this.matchesPattern(EMAIL_PATTERN,email);
        }
    }

    private boolean checkPassword(String password){
        return !this.isEmptyOrNull(password);
    }

    private boolean checkLogin(String login){
        return !this.isEmptyOrNull(login);
    }

    private boolean checkBirthDate(String date){

        if(this.isEmptyOrNull(date)){
            return false;
        }else {
            Date currentDate = new Date();
            try {
                Date registrationDate = formatter.parse(date);

                if(registrationDate.after(currentDate)){
                    return false;
                }else {
                    return true;
                }
            } catch (ParseException e) {
                throw new ValidatorException(e);
            }
        }
    }


    @Override
    public boolean loginValidate(String email, String password) {

        if(this.isEmptyOrNull(password) || this.isEmptyOrNull(email)){
            return false;
        }else{
            return this.matchesPattern(EMAIL_PATTERN,email);
        }

    }

}
