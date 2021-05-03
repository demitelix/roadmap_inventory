package lv.acc.springboot.validators;

import lv.acc.springboot.exceptions.BookNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
class ResultValidatorsTest {

    @Autowired
    private ResultValidators resultValidators;

    @Test
    void checkNotNull() {
        BookNotFoundException exception = assertThrows(BookNotFoundException.class, () -> resultValidators.checkNotNull(null));
        assertEquals("Book not found", exception.getMessage());
    }
}