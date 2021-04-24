package lv.acc.springboot.exceptions;

public class LessThanZeroException extends RuntimeException{
    public LessThanZeroException(String message){
        super(message);
    }
}
