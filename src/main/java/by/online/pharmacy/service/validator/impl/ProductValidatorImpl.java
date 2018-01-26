package by.online.pharmacy.service.validator.impl;
import by.online.pharmacy.controller.constant.ControllerConstant.ProductProperty;
import by.online.pharmacy.entity.Product;
import by.online.pharmacy.service.validator.ProductValidator;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Predicate;

public class ProductValidatorImpl extends AbstractValidator implements ProductValidator {

    private final Map<String,Predicate<String>> dispatcher = new HashMap<>();

    public ProductValidatorImpl(){

        dispatcher.put(ProductProperty.NAME_PARAMETER,checkForString());
        dispatcher.put(ProductProperty.DESCRIPTION_PARAMETER,checkForString());
        dispatcher.put(ProductProperty.COUNT_PARAMETER,checkForNumber());
        dispatcher.put(ProductProperty.PRICE_PARAMETER,checkForNumber());
    }

    @Override
    public boolean validate(final Product product) {
        return true;
    }

    @Override
    public boolean parametersValidate(final Map<String, String> parameters) {

        final AtomicBoolean result = new AtomicBoolean(true);
        dispatcher.forEach((key,value) ->{
            String parameter = parameters.get(key);
            if(!dispatcher.get(key).test(parameter)){
                result.set(false);
                return;
            }
        });

        return result.get();
    }



    private Predicate<String> checkForNumber(){
        return (parameter) -> !this.isEmptyOrNull(parameter)
                && this.isPositiveDigitsOnly(parameter);
    }

    private Predicate<String> checkForString(){
        return (parameter) -> !this.isEmptyOrNull(parameter)
                && this.isLettersOnly(parameter);
    }

}
