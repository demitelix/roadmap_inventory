package lv.acc.springboot.service;

import lv.acc.springboot.model.AcceptanceStatus;
import lv.acc.springboot.model.Book;
import lv.acc.springboot.model.BookStatus;
import lv.acc.springboot.storage.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookManagmentServiceImpl implements BookManagmentService {

    @Autowired
    Database db;

    @Override
    public List<Book> showAllBooks() {
        return db.getAllBooks();
    }

    @Override
    public AcceptanceStatus addNewBook(Book book) {
        //check if title and author filled
        book.setBookStatus(BookStatus.AVAILABLE);
        AcceptanceStatus acceptanceStatus = db.addBook(book);
        return acceptanceStatus;
    }

    @Override
    public List<Book> findBookByTitle(String title) {
        //check id not null
        List<Book> listOfBooks = db.findByTitle(title);
        //check not null
        return listOfBooks;
    }

    @Override
    public List<Book> findBookById(Long id) {
        //check id not null
        List<Book> books = db.findByIds(id);
        //check not null
        return books;
    }

    @Override
    public void changeBookStatus(Long id, BookStatus bookStatus) {
        List<Book> books = db.findByIds(id);
        Book book = books.get(0);
        //Validate if not null
        book.setBookStatus(bookStatus);
        db.setNewStatus(id, book);
    }
}
