package lv.acc.springboot.service.validators;

import lv.acc.springboot.model.Book;
import org.springframework.stereotype.Component;


@Component
public class InputValidators {
    public void validateBookInput(Book book) throws Exception{
        if (book.getTitle().isEmpty() || book.getTitle().isBlank()){
            throw new Exception("Title field is empty");
        }
        if (book.getAuthor().isEmpty() || book.getAuthor().isBlank()){
            throw new Exception("Author field is empty");
        }
    }

    public void validateTitleInput(String title) throws Exception{
        if (title.isBlank() || title.isEmpty()){
            throw new Exception("Title field is empty");
        }
    }

    public void validateIdInput(Long id) throws Exception{
        if (id == null){
            throw new Exception("id field is empty");
        }
        if (id <= 0L){
            throw new Exception("id must be greater than 0");
        }
    }
}
