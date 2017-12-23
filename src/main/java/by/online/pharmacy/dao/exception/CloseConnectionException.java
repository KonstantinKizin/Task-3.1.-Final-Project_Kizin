package by.online.pharmacy.dao.exception;


public class CloseConnectionException extends RuntimeException {

    public CloseConnectionException() {
        super();
    }

    public CloseConnectionException(String message) {
        super(message);
    }

    public CloseConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public CloseConnectionException(Throwable cause) {
        super(cause);
    }
}
