package by.online.pharmacy.service.validator;

import by.online.pharmacy.entity.Customer;
import by.online.pharmacy.service.validator.impl.CustomerValidatorImpl;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.List;

public class CustomerRegistrationValidatorTest {


    private final Customer customer = new Customer();

    private final CustomerValidator validator = new CustomerValidatorImpl();

    @Before
    public void setUp(){

        customer.setDateOfBirth("1995-09-08");
        customer.setEmail("KostyKizin@gmail.com");
        customer.setGender("M");
        customer.setLogin("kos51");
        customer.setName("Konstantin");
        customer.setSureName("Kizin");
        customer.setPassword("481415");
        customer.setPhoneNumber("+375296471934");
        customer.setRegistrationDate("2017-01-02");
        customer.setRole("Customer");
    }

    @Test
    public void when_dateOfBirthd_happened_after_currentDate_then_size_should_increase_at_one(){

        customer.setDateOfBirth("2120-09-11");
        List<String> errors = validator.registrationValidate(customer);
        assertThat(errors.size(),is(1));
    }

    @Test
    public void when_dateOfBirth_happened_before_currentDate_then_size_should_not_change(){
        List<String> errors = validator.registrationValidate(customer);
        assertThat(errors.size(),is(0));
    }


    @Test
    public void when_phone_number_has_letter_then_size_should_increase_at_one(){

        customer.setPhoneNumber("37529456kj32");
        List<String> errors = validator.registrationValidate(customer);
        assertThat(errors.size(),is(1));
    }


    @Test
    public void return_correct_errors_list_test(){

        customer.setDateOfBirth("2025-09-08");
        customer.setEmail("KostyKizin@s");
        customer.setPhoneNumber("+375296471934");
        List<String> errors = validator.registrationValidate(customer);
        assertThat(errors.size(), is(3));
        assertThat(errors.contains("CUSTOMER_EMAIL_ERROR"),is(true));
        assertThat(errors.contains("CUSTOMER_PHONE_ERROR"),is(true));
        assertThat(errors.contains("CUSTOMER_BIRTH_DATE_ERROR"),is(true));
        assertThat(errors.contains("CUSTOMER_NAME_ERROR"),is(false));
    }

















}
