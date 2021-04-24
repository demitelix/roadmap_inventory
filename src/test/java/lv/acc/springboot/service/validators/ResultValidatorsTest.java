package lv.acc.springboot.service.validators;

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
        Exception exception = assertThrows(Exception.class, () -> resultValidators.checkNotNull(null));
        assertEquals("Book not found", exception.getMessage());
    }
}