package by.online.pharmacy.service.validator.impl;
import by.online.pharmacy.entity.Customer;
import by.online.pharmacy.service.exception.ValidatorException;
import by.online.pharmacy.service.validator.CustomerValidator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.HashMap;
import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import static by.online.pharmacy.controller.constant.ControllerConstant.RegistrationProperty;

public class CustomerValidatorImpl extends AbstractValidator implements CustomerValidator<Customer> {

    private static final String EMAIL_REG_EXP = "([a-zA-Z0-9]+)(@)([a-zA-Z]+)(\\.)([a-zA-Z]){2,3}";

    private static final String PHONE_NUMBER_REG_EXP = "^(\\+?)(\\d){10,12}|^(\\+?)(\\d){3}-(\\d){9}|^(\\+?)(\\d){3}-(\\d){2}-(\\d){7,9}";;

    private static final String NAME_REG_EXP = "([a-zA-Z]+)";

    private static final Pattern PHONE_NUMBER_PATTERN = Pattern.compile(PHONE_NUMBER_REG_EXP);

    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REG_EXP);

    private static final Pattern NAME_PATTER = Pattern.compile(NAME_REG_EXP);

    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd");

    private final Map<String,Predicate<Customer>> dispathcer = new HashMap<>();


    public CustomerValidatorImpl(){

        dispathcer.put(RegistrationProperty.NAME_PARAMETER,checkName());
        dispathcer.put(RegistrationProperty.SURE_NAME_PARAMETER,checkSecondName());
        dispathcer.put(RegistrationProperty.PW_PARAMETER,checkPassword());
        dispathcer.put(RegistrationProperty.EMAIL_PARAMETER,checkEmail());
        dispathcer.put(RegistrationProperty.PHONE_PARAMETER,checkPhone());
        dispathcer.put(RegistrationProperty.LOGIN_PARAMETER,checkLogin());
        dispathcer.put(RegistrationProperty.BIRTH_DATE_PARAMETER,checkBirthDate());

    }


    @Override
    public boolean validate(Customer customer) {
        AtomicBoolean result = new AtomicBoolean(true);
        dispathcer.forEach((key,value)->{
            if(!dispathcer.get(key).test(customer)){
                result.set(false);
                return;
            }
        });
        return result.get();
    }

    @Override
    public boolean loginValidate(String email, String password) {

        if(this.isEmptyOrNull(email) || this.isEmptyOrNull(password)){
            return false;
        }else {
            return this.matchesPattern(EMAIL_PATTERN,email);
        }
    }


    private Predicate<Customer> checkName(){
        return (customer)-> !this.isEmptyOrNull(customer.getName())
                &&
                this.matchesPattern(NAME_PATTER,customer.getName());
    }

    private Predicate<Customer> checkSecondName(){
        return (customer)-> !this.isEmptyOrNull(customer.getSureName())
                &&
                this.matchesPattern(NAME_PATTER,customer.getSureName());
    }


    private Predicate<Customer> checkBirthDate(){
        return (customer)-> !this.isEmptyOrNull(customer.getDateOfBirth())
                &&
                this.isCurrentDateAfter(customer.getDateOfBirth());
    }

    private Predicate<Customer> checkEmail(){
        return (customer) -> !this.isEmptyOrNull(customer.getEmail())
                &&
                this.matchesPattern(EMAIL_PATTERN,customer.getEmail());
    }

    private Predicate<Customer> checkPhone(){
        return (customer) -> !this.isEmptyOrNull(customer.getPhoneNumber())
                &&
                this.matchesPattern(PHONE_NUMBER_PATTERN,customer.getPhoneNumber());
    }


    private Predicate<Customer> checkPassword(){
        return (customer) -> !this.isEmptyOrNull(customer.getPassword());

    }

    private Predicate<Customer> checkLogin(){
        return (customer) -> !this.isEmptyOrNull(customer.getLogin());
    }

    private boolean isCurrentDateAfter(String regDate){
        Date birthDate = null;
        Date currentDate = null;
        try {
            birthDate = FORMATTER.parse(regDate);
            currentDate = new Date();
            return currentDate.after(birthDate);
        } catch (ParseException e) {
            throw new ValidatorException(e);
        }
    }

}
