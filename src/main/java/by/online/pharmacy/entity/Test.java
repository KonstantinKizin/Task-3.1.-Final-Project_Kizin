package by.online.pharmacy.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Test {

    private final static Map<Message.Type , Function<Message,Boolean>> dispatcher = new HashMap<>();



    private void load(Message.Type type , Function<Message,Boolean> handler){
        dispatcher.put(type,handler);
    }

    private void init(){
        this.load(Message.Type.MAIL,toMail());
        this.load(Message.Type.UNKNOWN,unknown());
    }



    public static void main(String[] args) {




    }


    private Function<Message,Boolean> toMail(){
        return msg -> {
            return true;
        };
    }


    private Function<Message,Boolean> unknown(){
        return msg -> {
            return false;
        };
    }

    private boolean sent(Message msg){
        return dispatcher.get(msg.type()).apply(msg);
    }


}
