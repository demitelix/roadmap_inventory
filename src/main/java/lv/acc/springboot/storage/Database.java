package lv.acc.springboot.storage;

import lv.acc.springboot.model.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface Database extends CrudRepository<Book, Long> {
    List<Book> findByTitleContains(String title);
}
