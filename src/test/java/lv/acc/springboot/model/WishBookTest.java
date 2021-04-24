package lv.acc.springboot.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class WishBookTest {
    public static final String EXPECTED_TITLE = "title_1";
    public static final String EXPECTED_AUTHOR = "author_1";
    public static final Long EXPECTED_ID = 1L;
    public static final BookStatus EXPECTED_BOOK_STATUS = BookStatus.AVAILABLE;
    public static final String EXPECTED_TITLE_UPDATED = "title_2";
    public static final String EXPECTED_AUTHOR_UPDATED = "author_2";
    public static final Long EXPECTED_ID_UPDATED = 2L;
    public static final BookStatus EXPECTED_BOOK_STATUS_UPDATED = BookStatus.RESERVED;
    private WishBook book;

    @BeforeEach
    public void setUp() {
        book = new WishBook(1L,"title_1","author_1",BookStatus.AVAILABLE);
    }

    @Test
    void testBookDetails(){
        assertEquals(EXPECTED_TITLE,book.getTitle());
        assertEquals(EXPECTED_AUTHOR,book.getAuthor());
        assertEquals(EXPECTED_ID,book.getId());
        assertEquals(EXPECTED_BOOK_STATUS,book.getBookStatus());
    }

    @Test
    void testBookAfterUpdate(){
        book.setId(2L);
        book.setTitle("title_2");
        book.setAuthor("author_2");
        book.setBookStatus(BookStatus.RESERVED);

        assertEquals(EXPECTED_TITLE_UPDATED,book.getTitle());
        assertEquals(EXPECTED_AUTHOR_UPDATED,book.getAuthor());
        assertEquals(EXPECTED_ID_UPDATED,book.getId());
        assertEquals(EXPECTED_BOOK_STATUS_UPDATED,book.getBookStatus());
    }
}