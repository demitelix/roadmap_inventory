package lv.acc.springboot.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter @Setter
@Entity
public class Book {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String author;
    private BookStatus bookStatus;

    public Book(Long id, String title, String author, BookStatus bookStatus) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.bookStatus = bookStatus;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", bookStatus=" + bookStatus +
                '}';
    }
}
