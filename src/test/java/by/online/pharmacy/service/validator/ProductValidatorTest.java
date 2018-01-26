package by.online.pharmacy.service.validator;

import by.online.pharmacy.service.validator.impl.ProductValidatorImpl;
import org.junit.Before;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import java.util.HashMap;
import java.util.Map;
import by.online.pharmacy.controller.constant.ControllerConstant.ProductProperty;
import org.junit.Test;


public class ProductValidatorTest {

    private final Map<String,String> pMap = new HashMap<>();
    private final ProductValidator validator = new ProductValidatorImpl();

    @Before
    public void setUp(){
        pMap.put(ProductProperty.NAME_PARAMETER,"Correct Name");
        pMap.put(ProductProperty.DESCRIPTION_PARAMETER,"Correct description");
        pMap.put(ProductProperty.COUNT_PARAMETER,"10");
        pMap.put(ProductProperty.PRICE_PARAMETER,"15.5");
    }

    @Test
    public void when_all_parameters_correct_should_return_true(){
        assertThat(validator.parametersValidate(pMap),is(true));
    }
    @Test
    public void when_put_empty_description_then_should_return_false(){
        pMap.replace(ProductProperty.DESCRIPTION_PARAMETER,"");
        assertThat(validator.parametersValidate(pMap),is(false));
    }
    @Test
    public void when_put_empty_count_then_should_return_false(){
        pMap.replace(ProductProperty.COUNT_PARAMETER,"");
        assertThat(validator.parametersValidate(pMap),is(false));
    }
    @Test
    public void when_put_empty_price_then_should_return_false(){
        pMap.replace(ProductProperty.PRICE_PARAMETER,"");
        assertThat(validator.parametersValidate(pMap),is(false));
    }

    @Test
    public void when_put_empty_name_then_should_return_false(){
        pMap.replace(ProductProperty.NAME_PARAMETER,"");
        assertThat(validator.parametersValidate(pMap),is(false));
    }

  @Test
    public void when_price_contain_a_character_then_should_return_false(){
      pMap.replace(ProductProperty.PRICE_PARAMETER,"123s");
      assertThat(validator.parametersValidate(pMap),is(false));
  }

    @Test
    public void when_count_contain_a_character_then_should_return_false(){
        pMap.replace(ProductProperty.COUNT_PARAMETER,"123s");
        assertThat(validator.parametersValidate(pMap),is(false));
    }


}
