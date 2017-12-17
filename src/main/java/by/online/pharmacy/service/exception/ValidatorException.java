package by.online.pharmacy.service.exception;

public class ValidatorException extends RuntimeException {

    public ValidatorException(){
        super();
    }

    public ValidatorException(String message , Throwable cause){
        super(message,cause);
    }

    public ValidatorException(String message){
        super(message);
    }

    public ValidatorException(Throwable cause){
        super(cause);
    }


}
