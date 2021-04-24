package lv.acc.springboot.service.validators;

import lv.acc.springboot.model.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class InputValidatorsTest {

    @Autowired
    private InputValidators inputValidators;

    @Test
    void validateBookInput_emptyAuthor() {
        Book book = new Book();
        book.setTitle("Title_1");
        book.setAuthor("");

        Exception exception = assertThrows(Exception.class, () -> inputValidators.validateBookInput(book));
        assertEquals("Author field is empty", exception.getMessage());
    }

    @Test
    void validateBookInput_emptyTitle() {
        Book book = new Book();
        book.setTitle("");
        book.setAuthor("Any");

        Exception exception = assertThrows(Exception.class, () -> inputValidators.validateBookInput(book));
        assertEquals("Title field is empty", exception.getMessage());
    }

    @Test
    void validateTitleInput() {
        Exception exception = assertThrows(Exception.class, () -> inputValidators.validateTitleInput(""));
        assertEquals("Title field is empty", exception.getMessage());
    }

    @Test
    void validateIdInput(){
        Exception emptyFieldException = assertThrows(Exception.class, () -> inputValidators.validateIdInput(null));
        assertEquals("id field is empty", emptyFieldException.getMessage());

        Exception lessThanZeroException = assertThrows(Exception.class, () -> inputValidators.validateIdInput(-1L));
        assertEquals("id must be greater than 0", lessThanZeroException.getMessage());
    }
}