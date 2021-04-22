package lv.acc.springboot.service;

import lv.acc.springboot.model.AcceptanceStatus;
import lv.acc.springboot.model.Book;
import lv.acc.springboot.model.BookStatus;
import lv.acc.springboot.storage.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Component
public class BookManagmentServiceImpl implements BookManagmentService {

    @Autowired
    Database db;

    @Override
    public List<Book> showAllBooks() {

        return (List<Book>) db.findAll();
    }

    @Override
    public AcceptanceStatus addNewBook(Book book) {
        //check if title and author filled create VALIDATOR with TRY CATCH
        if (book.getTitle() != null && book.getAuthor() != null
                && !book.getTitle().isEmpty() && !book.getAuthor().isEmpty()) {
            book.setBookStatus(BookStatus.AVAILABLE);
            db.save(book);
            return AcceptanceStatus.SUCCESSFUL;
        } else
            return AcceptanceStatus.REJECTED;
    }

    @Override
    public List<Book> findBookByTitle(String title) {
        //check id not null
        return db.findByTitleContains(title);
    }

    @Override
    public List<Book> findBookById(Long id) {
        //check id not null
        List<Book> books = new ArrayList<>();
        //check not null validator
        Book book = db.findById(id).orElse(null);
        if (book == null) {
            return Collections.emptyList();
        } else {
            books.add(book);
            return books;
        }


    }

    @Override
    public void changeBookStatus(Long id, BookStatus bookStatus) {
        Book book = db.findById(id).orElse(null);
        if (book != null) {
            book.setBookStatus(bookStatus);
            db.save(book);
        }
    }
}
