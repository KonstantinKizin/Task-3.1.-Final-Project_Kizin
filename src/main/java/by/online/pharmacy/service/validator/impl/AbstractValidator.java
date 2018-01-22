package by.online.pharmacy.service.validator.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractValidator{

    protected boolean isEmptyOrNull(String parameter){

        if(parameter == null || parameter.isEmpty()){
            return true;
        }else {
            return false;
        }
    }

    protected boolean isLettersOnly(String parameter){
        char[] letters = parameter.toCharArray();

        for(char buf : letters){
            if(buf >= 48 && buf <=57 ){
                return false;
            }
        }
        return true;
    }

    protected boolean hasDigits(String parameter){
        return !isLettersOnly(parameter);
    }

    protected boolean isDigitsOnly(String parameter){
        char[] letters = parameter.toCharArray();

        for(char buf : letters){
            if(buf >= 48 && buf <=57 || buf==46){
                continue;
            }else {
                return false;
            }
        }
        return true;
    }

    protected boolean findPattern(Pattern pattern , String expression){
        Matcher matcher = pattern.matcher(expression);
        return matcher.find();
    }

    protected boolean matchesPattern(Pattern pattern , String expression){
        Matcher matcher = pattern.matcher(expression);
        return matcher.matches();
    }






}
