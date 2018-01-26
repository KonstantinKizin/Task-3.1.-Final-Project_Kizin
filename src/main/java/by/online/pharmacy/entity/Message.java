package by.online.pharmacy.entity;

public interface Message {

    Type type();

    enum Type{
        MAIL,
        UNKNOWN
    }

}
