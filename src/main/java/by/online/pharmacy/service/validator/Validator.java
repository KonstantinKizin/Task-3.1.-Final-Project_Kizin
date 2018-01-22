package by.online.pharmacy.service.validator;

import by.online.pharmacy.entity.ValidationError;

import java.util.List;

public interface Validator<T> {

    List<ValidationError> validate(T t);

}
