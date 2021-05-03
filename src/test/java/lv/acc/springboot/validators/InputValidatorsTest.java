package lv.acc.springboot.validators;

import lv.acc.springboot.exceptions.EmptyFieldException;
import lv.acc.springboot.exceptions.LessThanZeroException;
import lv.acc.springboot.model.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
class InputValidatorsTest {

    @Autowired
    private InputValidators inputValidators;

    @Test
    void validateBookInput_emptyAuthor() {
        Book book = new Book();
        book.setTitle("Title_1");
        book.setAuthor("");

        EmptyFieldException exception = assertThrows(EmptyFieldException.class, () -> inputValidators.validateBookInput(book));
        assertEquals("Author field is empty", exception.getMessage());
    }

    @Test
    void validateBookInput_emptyTitle() {
        Book book = new Book();
        book.setTitle("");
        book.setAuthor("Any");

        EmptyFieldException exception = assertThrows(EmptyFieldException.class, () -> inputValidators.validateBookInput(book));
        assertEquals("Title field is empty", exception.getMessage());
    }

    @Test
    void validateTitleInput() {
        EmptyFieldException exception = assertThrows(EmptyFieldException.class, () -> inputValidators.validateTitleOrAuthorInput(""));
        assertEquals("Title field is empty", exception.getMessage());
    }

    @Test
    void validateIdInput(){
        EmptyFieldException emptyFieldException = assertThrows(EmptyFieldException.class, () -> inputValidators.validateIdInput(null));
        assertEquals("id field is empty", emptyFieldException.getMessage());

        LessThanZeroException lessThanZeroException = assertThrows(LessThanZeroException.class, () -> inputValidators.validateIdInput(-1L));
        assertEquals("id must be greater than 0", lessThanZeroException.getMessage());
    }
}