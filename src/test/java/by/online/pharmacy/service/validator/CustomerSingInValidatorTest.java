package by.online.pharmacy.service.validator;
import static org.junit.Assert.assertThat;
import by.online.pharmacy.service.validator.impl.CustomerValidatorImpl;
import org.junit.Test;
import static org.hamcrest.core.Is.is;

public class CustomerSingInValidatorTest {

    private final CustomerValidator validator = new CustomerValidatorImpl();

    
    @Test
    public void loginValidateTest_WhenPutEmptyEmailOrPassword_then_ShouldReturnFalse(){
        boolean result = validator.loginValidate("","password");
        assertThat(result, is(false));
        result = validator.loginValidate("email@email.com","");
        assertThat(result, is(false));
    }

    @Test
    public void loginValidateTest_WhenPutNull_then_ShouldReturnFalse(){
        boolean result = validator.loginValidate(null,"password");
        assertThat(result, is(false));
        result = validator.loginValidate("email@email.com",null);
        assertThat(result, is(false));
    }


    @Test
    public void loginValidateTest_WhenPutIncorrectEmail_then_ShouldReturnFalse(){
        boolean result = validator.loginValidate("email@","password");
        assertThat(result, is(false));
    }

    @Test
    public void loginValidateTest_WhenPutCorrectEmail_AndPassword_IsNotEmptyAndNotNull_then_ShouldReturnTrue(){
        boolean result = validator.loginValidate("email@email.com","password");
        assertThat(result, is(true));
    }











}
