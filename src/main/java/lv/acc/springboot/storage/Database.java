package lv.acc.springboot.storage;

import lv.acc.springboot.model.AcceptanceStatus;
import lv.acc.springboot.model.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface Database extends CrudRepository <Book, Long> {
    List<Book> findByTitle(String title);
    List<Book> findByIds(Long id);
    AcceptanceStatus addBook(Book book);
    List<Book> getAllBooks();
    void setNewStatus(Long id, Book book);
    AcceptanceStatus deleteBook(Book book);
}
