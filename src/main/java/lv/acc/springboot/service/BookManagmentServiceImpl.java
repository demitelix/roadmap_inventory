package lv.acc.springboot.service;

import lv.acc.springboot.model.AcceptanceStatus;
import lv.acc.springboot.model.Book;
import lv.acc.springboot.model.BookStatus;
import lv.acc.springboot.service.validators.InputValidators;
import lv.acc.springboot.service.validators.ResultValidators;
import lv.acc.springboot.storage.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;


@Component
public class BookManagmentServiceImpl implements BookManagmentService {

    @Autowired
    Database db;

    @Autowired
    InputValidators validator;

    @Autowired
    ResultValidators resultValidators;

    @Override
    public List<Book> showAllBooks() {

        return (List<Book>) db.findAll();
    }

    @Override
    public AcceptanceStatus addNewBook(Book book) {
        try {

            validator.validateBookInput(book);
            book.setBookStatus(BookStatus.AVAILABLE);
            db.save(book);
            return AcceptanceStatus.SUCCESSFUL;
        } catch (Exception e) {
            e.printStackTrace();
            return AcceptanceStatus.REJECTED;
        }
    }

    @Override
    public List<Book> findBookByTitle(String title) {
        try {
            validator.validateTitleInput(title);
            return db.findByTitleContains(title);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public List<Book> findBookById(Long id) {
        try {
            validator.validateIdInput(id);
            Book book = db.findById(id).orElse(null);
            resultValidators.checkNotNull(book);
            return List.of(Objects.requireNonNull(book));
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
aaa
    }

    @Override
    public void changeBookStatus(Long id, BookStatus bookStatus) {
        try {
            validator.validateIdInput(id);
            Book book = db.findById(id).orElse(null);
            resultValidators.checkNotNull(book);
            Objects.requireNonNull(book).setBookStatus(bookStatus);
            db.save(book);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
