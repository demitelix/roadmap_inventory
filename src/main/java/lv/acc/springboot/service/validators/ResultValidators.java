package lv.acc.springboot.service.validators;

import lv.acc.springboot.model.Book;
import org.springframework.stereotype.Component;

@Component
public class ResultValidators {
    public void checkNotNull(Book book) throws Exception{
        if (book == null){
            throw new Exception("Book not found");
        }
    }
}
