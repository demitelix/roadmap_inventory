package lv.acc.springboot.storage;

import lv.acc.springboot.model.Book;
import org.springframework.data.repository.CrudRepository;


public interface Database extends CrudRepository<Book, Long> {

}
