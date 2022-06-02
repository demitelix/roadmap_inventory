package lv.acc.springboot.controller;

import lv.acc.springboot.exceptions.EmptyFieldException;
import lv.acc.springboot.exceptions.LessThanZeroException;
import lv.acc.springboot.model.AcceptanceStatus;
import lv.acc.springboot.model.Book;
import lv.acc.springboot.model.BookStatus;
import lv.acc.springboot.model.WishBook;
import lv.acc.springboot.service.BookManagmentService;
import lv.acc.springboot.validators.InputValidators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
public class InventoryMainController {

    private String viewbooks = "viewbooks";

//    @Autowired
    private final BookManagmentService service;

    public InventoryMainController(final BookManagmentService service, final InputValidators inputValidators) {
        this.service = service;
        this.inputValidators = inputValidators;
    }

    //    @Autowired
    private final InputValidators inputValidators;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/allBooks")
    public String allBooks(Model model) {
        model.addAttribute("all", service.showAllBooks());
        return viewbooks;
    }

    @GetMapping("/{id}/returnBook")
    public String returnBook(@PathVariable("id") Long id) {
        try {
            inputValidators.validateIdInput(id);
            service.changeBookStatus(id, BookStatus.AVAILABLE);
        } catch (EmptyFieldException | LessThanZeroException e) {
            e.printStackTrace();
        }
        return "redirect:/allBooks";
    }

    @GetMapping("/{id}/reserveBook")
    public String reserveBook(@PathVariable("id") Long id) {
        try {
            inputValidators.validateIdInput(id);
            service.changeBookStatus(id, BookStatus.RESERVED);
        } catch (EmptyFieldException | LessThanZeroException e) {
            e.printStackTrace();
        }
        return "redirect:/allBooks";
    }

    @GetMapping("/newbookform")
    public String newBookForm(@ModelAttribute("book") WishBook book) {
        return "newbookform";
    }

    @PostMapping("/addnewbook")
    public String addNewBook(@ModelAttribute("book") WishBook book, Model model, RedirectAttributes redirAttrs) {
        try {
            inputValidators.validateTitleOrAuthorInput(book.getTitle());
            inputValidators.validateTitleOrAuthorInput(book.getAuthor());
            Book bookModel = new Book();
            bookModel.setTitle(book.getTitle());
            bookModel.setAuthor(book.getAuthor());
            bookModel.setBookStatus(BookStatus.RESERVED);
            String procedureStatus = service.addNewBook(bookModel).toString();
            model.addAttribute("statusMessage", procedureStatus);
            redirAttrs.addFlashAttribute("success", "Everything went just fine.");
        } catch (EmptyFieldException e) {
            e.printStackTrace();
            redirAttrs.addFlashAttribute("error", "The error XYZ occurred.");
            model.addAttribute("statusMessage", AcceptanceStatus.REJECTED);
        }
        return "redirect:/allBooks";
    }

    @GetMapping("/findbyidform")
    public String findByIdForm(@ModelAttribute("book") WishBook book) {
        return "findbyidform";
    }

    @PostMapping("/findbyid")
    public String findById(@ModelAttribute("book") WishBook book, Model model) {
        try {
            inputValidators.validateIdInput(book.getId());
            List<Book> listOfBooks = service.findBookById(book.getId());
            if (listOfBooks.isEmpty()) {
                model.addAttribute("error", "Book with ID not found");
                return "nothingfound";
            }
            model.addAttribute("all", listOfBooks);
            model.addAttribute("error", "");
            return viewbooks;
        } catch (EmptyFieldException | LessThanZeroException e) {
            System.out.println(e.getMessage());
            model.addAttribute("error", e.getMessage());
            return "findbyidform";
        }
    }

    @GetMapping("/findbytitleform")
    public String findByTitleForm(@ModelAttribute("book") WishBook book) {
        return "findbytitleform";
    }

    @PostMapping("/findbytitle")
    public String findByTitle(@ModelAttribute("book") WishBook book, Model model) {
        try {
            inputValidators.validateTitleOrAuthorInput(book.getTitle());
            List<Book> listOfBooks = service.findBookByTitle(book.getTitle());
            if (listOfBooks.isEmpty()) {
                model.addAttribute("error", "Book with such criteria not found");
                return "nothingfound";
            }
            model.addAttribute("all", listOfBooks);
            return viewbooks;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            model.addAttribute("error", e.getMessage());
            return "findbytitleform";
        }
    }
}