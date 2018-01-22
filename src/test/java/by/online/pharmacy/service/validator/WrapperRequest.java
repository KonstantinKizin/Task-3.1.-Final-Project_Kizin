package by.online.pharmacy.service.validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.HashMap;
import java.util.Map;


public class WrapperRequest extends HttpServletRequestWrapper {

    private Map<String,String> parameters = new HashMap<>();


    public WrapperRequest(HttpServletRequest request) {
        super(request);
    }

    public String getParameter(String parameter){
        return this.parameters.get(parameter);
    }

    public void setParameter(String parameterName, String parameterValue){

        if(this.parameters.containsKey(parameterName)){
            this.parameters.replace(parameterName,parameterValue);
        }else {
            this.parameters.put(parameterName,parameterValue);
        }

    }
}
