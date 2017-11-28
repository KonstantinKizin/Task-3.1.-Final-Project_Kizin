package by.online.pharmacy.controller.exception;

public class InitializeServletException extends RuntimeException {

    public InitializeServletException(){
        super();
    }

    public InitializeServletException(String message , Throwable cause){
        super(message,cause);
    }

    public InitializeServletException(String message){
        super(message);
    }

    public InitializeServletException(Throwable cause){
        super(cause);
    }



}
