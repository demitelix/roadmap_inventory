package lv.acc.springboot.exceptions;

public class EmptyFieldException extends NoSuchFieldException{
    public EmptyFieldException(String message){
        super(message);
    }
}
