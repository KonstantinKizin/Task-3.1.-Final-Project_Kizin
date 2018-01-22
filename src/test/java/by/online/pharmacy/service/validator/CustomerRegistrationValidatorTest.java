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

    private final by.online.pharmacy.service.validator.CustomerValidator validator = new CustomerValidatorImpl();

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
    public void when_dateOfBirth_happened_after_currentDate_then_list_should_increase_at_one(){

        customer.setDateOfBirth("2120-09-11");
        List errors = validator.validate(customer);
        assertThat(errors.size(),is(2));
    }

    @Test
    public void when_dateOfBirth_happened_before_currentDate_then_list_should_not_change(){
        List errors = validator.validate(customer);
        assertThat(errors.size(),is(0));
    }


    @Test
    public void when_phone_number_has_a_letter_then_list_should_increase_at_one(){

        customer.setPhoneNumber("+375296471934s");
        List errors = validator.validate(customer);
        assertThat(errors.size(),is(2));
    }

    @Test
    public void when_put_email_without_point_behind_domain_then_should_return_increased_at_one_list(){
        customer.setEmail("KostyKizin@gmailcom");
        List errors = validator.validate(customer);
        assertThat(errors.size(), is(2));
    }

    @Test
    public void when_put_email_without_domain_separator_symbol_then_should_return_increased_at_one_llist(){
        customer.setEmail("KostyKizingmailcom");
        List errors = validator.validate(customer);
        assertThat(errors.size(), is(2));
    }

    @Test
    public void when_put_email_without_domain_then_should_return_increased_at_one_llist(){
        customer.setEmail("KostyKizin@.com");
        List errors = validator.validate(customer);
        assertThat(errors.size(), is(2));
    }

    @Test
    public void when_put_correct_email_then_should_return_unchanged_list(){
        customer.setEmail("KostyKizin@gmail.com");
        List errors = validator.validate(customer);
        assertThat(errors.size(), is(0));

    }

    @Test
    public void when_put_login_as_null_or_empty_then_should_return_increased_at_one_list(){
        customer.setLogin("");
        List errors = validator.validate(customer);
        assertThat(errors.size(), is(2));
        customer.setLogin(null);
        errors = validator.validate(customer);
        assertThat(errors.size(), is(2));

    }



    @Test
    public void when_put_password_as_null_or_empty_then_should_return_increased_at_one_list(){
        customer.setPassword("");
        List errors = validator.validate(customer);
        assertThat(errors.size(), is(2));
        customer.setPassword(null);
        errors = validator.validate(customer);
        assertThat(errors.size(), is(2));
    }



















}
