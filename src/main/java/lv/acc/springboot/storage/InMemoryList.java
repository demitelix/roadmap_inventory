//package lv.acc.springboot.storage;
//
//import lv.acc.springboot.model.AcceptanceStatus;
//import lv.acc.springboot.model.Book;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//public abstract class InMemoryList implements Database{
//
//    private final List<Book>inMemoryDb = new ArrayList<>();
//
//    private Long uuid = 1L;
//
//    @Override
//    public List<Book> findByTitle(String title) {
//        List<Book>tmpList = new ArrayList<>();
//        for(Book it:inMemoryDb){
//            if(it.getTitle().contains(title)){
//                tmpList.add(it);
//            }
//        }
//        return tmpList;
//    }
//
//    @Override
//    public List<Book> findByIds(Long id) {
//        List<Book>tmpList = new ArrayList<>();
//        for (Book it:inMemoryDb){
//            if(it.getId().equals(id)){
//                tmpList.add(it);
//                return tmpList;
//            }
//        }
//        return null;
//    }
//
//    @Override
//    public AcceptanceStatus addBook(Book book) {
//        book.setId(uuid);
//        inMemoryDb.add(book);
//        uuid++;
//        return AcceptanceStatus.SUCCESSFUL;
//    }
//
//    @Override
//    public List<Book> getAllBooks() {
//        return inMemoryDb;
//    }
//
//    @Override
//    public void setNewStatus(Long id, Book book) {
//        for (Book it:inMemoryDb){
//            if (it.getId().equals(id)){
//                it.setBookStatus(book.getBookStatus());
//            }
//        }
//    }
//
//    @Override
//    public AcceptanceStatus deleteBook(Book book) {
//        return null;
//    }
//}
