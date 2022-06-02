package lv.acc.springboot.service;

import lombok.RequiredArgsConstructor;
import lv.acc.springboot.exceptions.BookNotFoundException;
import lv.acc.springboot.exceptions.EmptyFieldException;
import lv.acc.springboot.exceptions.LessThanZeroException;
import lv.acc.springboot.model.AcceptanceStatus;
import lv.acc.springboot.model.Book;
import lv.acc.springboot.model.BookStatus;
import lv.acc.springboot.validators.InputValidators;
import lv.acc.springboot.validators.ResultValidators;
import lv.acc.springboot.storage.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;


//@Service
@RequiredArgsConstructor
public class BookManagmentServiceImpl implements BookManagmentService {

//    @Autowired
    private final Database db;

//    @Autowired
    private final InputValidators validator;

//    @Autowired
    private final ResultValidators resultValidators;

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
        } catch (EmptyFieldException e) {
            e.printStackTrace();
            return AcceptanceStatus.REJECTED;
        }
    }

    @Override
    public List<Book> findBookByTitle(String title) {
        try {
            validator.validateTitleOrAuthorInput(title);
            return db.findByTitleContains(title);
        } catch (EmptyFieldException e) {
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
        } catch (EmptyFieldException | BookNotFoundException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }

    }

    @Override
    public void changeBookStatus(Long id, BookStatus bookStatus) {
        try {
            validator.validateIdInput(id);
            Book book = db.findById(id).orElse(null);
            resultValidators.checkNotNull(book);
            Objects.requireNonNull(book).setBookStatus(bookStatus);
            db.save(book);
        } catch (EmptyFieldException | LessThanZeroException | BookNotFoundException e) {
            e.printStackTrace();
        }
    }
}
