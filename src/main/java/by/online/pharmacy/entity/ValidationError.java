package by.online.pharmacy.entity;

import by.online.pharmacy.service.validator.Validator;

public class ValidationError {

    private String message;

    private String fieldName;

    public ValidationError(String message, String fieldName) {
        this.message = message;
        this.fieldName = fieldName;
    }

    public ValidationError(){}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }


    @Override
    public String toString() {
        return "ValidationError{" +
                "message='" + message + '\'' +
                ", fieldName='" + fieldName + '\'' +
                '}';
    }
}
