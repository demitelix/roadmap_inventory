package lv.acc.springboot.service;

import lv.acc.springboot.model.Book;
import lv.acc.springboot.model.BookStatus;
import lv.acc.springboot.storage.Database;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class BookManagmentServiceImplTest {

    @Mock
    private Database db;

    @InjectMocks
    private BookManagmentServiceImpl bookManagmentServiceImpl;

    @Test
    void showAllBooks() {
        Book[] bookArray = {
                new Book(1L, "Title_1", "Author_1", BookStatus.AVAILABLE),
                new Book(2L, "Title_2", "Author_2", BookStatus.RESERVED)};
        List<Book> testableListOfBooks = Arrays.asList(bookArray);
        when(db.findAll()).thenReturn(testableListOfBooks);
        List<Book> actualResult = bookManagmentServiceImpl.showAllBooks();
        assertEquals(testableListOfBooks,actualResult);
    }
}