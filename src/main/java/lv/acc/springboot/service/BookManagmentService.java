package lv.acc.springboot.service;

import lv.acc.springboot.model.AcceptanceStatus;
import lv.acc.springboot.model.Book;
import lv.acc.springboot.model.BookStatus;
import org.springframework.stereotype.Component;

import java.util.List;


public interface BookManagmentService {
    List<Book>showAllBooks();
    void changeBookStatus(Long id, BookStatus bookStatus);
    AcceptanceStatus addNewBook(Book book);
    List<Book> findBookById(Long id);
    List<Book> findBookByTitle(String title);
}
