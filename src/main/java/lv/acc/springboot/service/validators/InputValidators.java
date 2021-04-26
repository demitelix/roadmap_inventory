package lv.acc.springboot.service.validators;

import lv.acc.springboot.exceptions.EmptyFieldException;
import lv.acc.springboot.exceptions.LessThanZeroException;
import lv.acc.springboot.model.Book;
import org.springframework.stereotype.Component;


@Component
public class InputValidators {
    public void validateBookInput(Book book) throws EmptyFieldException {
        if (book.getTitle().isEmpty() || book.getTitle().isBlank()){
            throw new EmptyFieldException("Title field is empty");
        }
        if (book.getAuthor().isEmpty() || book.getAuthor().isBlank()){
            throw new EmptyFieldException("Author field is empty");
        }
    }

    public void validateTitleInput(String title) throws EmptyFieldException{
        if (title.isBlank() || title.isEmpty()){
            throw new EmptyFieldException("Title field is empty");
        }
    }

    public void validateIdInput(Long id) throws EmptyFieldException, LessThanZeroException {
        if (id == null){
            throw new EmptyFieldException("id field is empty");
        }
        if (id <= 0L){
            throw new LessThanZeroException("id must be greater than 0");
        }
    }
}
