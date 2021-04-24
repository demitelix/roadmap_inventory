package lv.acc.springboot.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class BookTest {
    public static final String EXPECTED_TITLE = "title_1";
    public static final String EXPECTED_AUTHOR = "author_1";
    public static final Long EXPECTED_ID = 1L;
    public static final BookStatus EXPECTED_BOOK_STATUS = BookStatus.AVAILABLE;
    private Book book;

    @BeforeEach
    public void setUp() {
        book = new Book(1L,"title_1","author_1",BookStatus.AVAILABLE);
    }

    @Test
    void testBookDetails(){
        assertEquals(EXPECTED_TITLE,book.getTitle());
        assertEquals(EXPECTED_AUTHOR,book.getAuthor());
        assertEquals(EXPECTED_ID,book.getId());
        assertEquals(EXPECTED_BOOK_STATUS,book.getBookStatus());

        book.setId(2L);
        assertEquals(2L,book.getId());
    }

    @Test
    void testBookToString(){
        String expected = "Book{id=1, title='title_1', author='author_1', bookStatus=AVAILABLE}";
        assertEquals(expected,book.toString());
    }
}