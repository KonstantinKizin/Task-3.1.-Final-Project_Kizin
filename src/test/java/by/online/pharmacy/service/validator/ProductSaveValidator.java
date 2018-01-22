package by.online.pharmacy.service.validator;

import by.online.pharmacy.service.validator.impl.ProductValidatorImpl;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ProductSaveValidator {

    private WrapperRequest wRequest;
    private MyHttpServletRequest request;
    private ProductValidator validator = new ProductValidatorImpl();


    @Before
    public void setUp(){
        request = new MyHttpServletRequest();
        wRequest = new WrapperRequest(request);
        wRequest.setParameter("product_price","10.0");
        wRequest.setParameter("product_count","10");
    }

    @After
    public void setDown(){
        request = null;
        wRequest = null;
    }


    @Test
    public void when_put_null_in_price_then_list_should_increase_at_one(){
        wRequest.setParameter("product_price",null);
        List errors = validator.validate(wRequest);
        assertThat(errors.size(),is(1));
    }

    @Test
    public void when_put_emty_string_in_price_then_list_should_increase_at_one(){
        wRequest.setParameter("product_price","");
        List errors = validator.validate(wRequest);
        assertThat(errors.size(),is(1));

    }

    @Test
    public void when_put_null_in_count_then_list_should_increase_at_one(){
        wRequest.setParameter("product_count",null);
        List errors = validator.validate(wRequest);
        assertThat(errors.size(),is(1));
    }

    @Test
    public void when_price_and_count_are_numbers_then_list_should_not_change(){
        assertThat(validator.validate(wRequest).size(), is(0));
    }

    @Test
    public void when_put_emty_string_in_count(){

        System.out.println(wRequest.getParameter("product_price"));

        wRequest.setParameter("product_count","");
        List errors = validator.validate(wRequest);
        assertThat(errors.size(),is(1));

    }

}
