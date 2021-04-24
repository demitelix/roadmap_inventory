package lv.acc.springboot.exceptions;

public class BookNotFoundException extends NullPointerException{
    public BookNotFoundException(String message){
        super(message);
    }
}
