package lv.acc.springboot.validators;

import lv.acc.springboot.exceptions.BookNotFoundException;
import lv.acc.springboot.model.Book;
import org.springframework.stereotype.Component;

@Component
public class ResultValidators {
    public void checkNotNull(Book book){
        if (book == null){
            throw new BookNotFoundException("Book not found");
        }
    }
}
