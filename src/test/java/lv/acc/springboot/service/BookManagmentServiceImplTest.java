package lv.acc.springboot.service;

import lv.acc.springboot.model.AcceptanceStatus;
import lv.acc.springboot.model.Book;
import lv.acc.springboot.model.BookStatus;
import lv.acc.springboot.storage.Database;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
class BookManagmentServiceImplTest {

    @MockBean
    private Database db;

    @Autowired
    private BookManagmentServiceImpl bookManagmentServiceImpl;

    @Test
    void showAllBooks() {
        Book[] bookArray = {
                new Book(1L, "Title_1", "Author_1", BookStatus.AVAILABLE),
                new Book(2L, "Title_2", "Author_2", BookStatus.RESERVED)};
        List<Book> testableListOfBooks = Arrays.asList(bookArray);
        when(db.findAll()).thenReturn(testableListOfBooks);

        List<Book> actualResult = bookManagmentServiceImpl.showAllBooks();
        assertEquals(testableListOfBooks, actualResult);
    }

    @Test
    void addNewBook_success() {
        Book book = new Book(1L, "title", "author", BookStatus.AVAILABLE);
        when(db.save(any(Book.class))).thenReturn(new Book());

        AcceptanceStatus actual = bookManagmentServiceImpl.addNewBook(book);
        assertEquals(AcceptanceStatus.SUCCESSFUL, actual);
    }

    @Test
    void addNewBook_failed() {
        Book book = new Book();
        book.setTitle("Title_1");
        book.setAuthor("");

        AcceptanceStatus actual = bookManagmentServiceImpl.addNewBook(book);
        assertEquals(AcceptanceStatus.REJECTED, actual);
    }

    @Test
    void findBookByTitle_success() {
        Book[] bookArray = {
                new Book(1L, "Title_1", "Author_1", BookStatus.AVAILABLE),
                new Book(2L, "Title_2", "Author_2", BookStatus.RESERVED)};
        List<Book> expectedListOfBooks = Arrays.asList(bookArray);
        when(db.findByTitleContains(anyString())).thenReturn(expectedListOfBooks);

        List<Book> actualListOfBooks = bookManagmentServiceImpl.findBookByTitle("any_book");
        assertEquals(expectedListOfBooks, actualListOfBooks);
    }

    @Test
    void findBookByTitle_failed() {
            List<Book> actual = bookManagmentServiceImpl.findBookByTitle("");
            assertEquals(Collections.emptyList(), actual);
    }

    @Test
    void findBookById_success(){
        Book book = new Book(1L, "Title_1", "Author_1", BookStatus.AVAILABLE);
        List<Book> expectedListOfBooks = Stream.of(book).collect(Collectors.toList());
        when(db.findById(anyLong())).thenReturn(java.util.Optional.of(book));

        List<Book>actual = bookManagmentServiceImpl.findBookById(1L);

        assertEquals(expectedListOfBooks,actual);
    }

    @Test
    void findBookById_failed(){
        List<Book>actualWhenLessThanZero = bookManagmentServiceImpl.findBookById(-1L);
        assertEquals(Collections.emptyList(),actualWhenLessThanZero);

        List<Book>actualWhenNull = bookManagmentServiceImpl.findBookById(null);
        assertEquals(Collections.emptyList(),actualWhenNull);
    }

    @Test
    void changeBookStatus_success(){
        Book book = new Book(1L, "Title_1", "Author_1", BookStatus.AVAILABLE);
        when(db.findById(anyLong())).thenReturn(java.util.Optional.of(book));

        bookManagmentServiceImpl.changeBookStatus(1L,BookStatus.RESERVED);

        verify(db).save(book);
        assertSame(BookStatus.RESERVED,book.getBookStatus());
    }
}