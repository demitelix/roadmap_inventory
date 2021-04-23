package lv.acc.springboot.controller;

import lv.acc.springboot.model.Book;
import lv.acc.springboot.model.BookStatus;
import lv.acc.springboot.service.BookManagmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class InventoryMainController {

    @Autowired
	BookManagmentService service;

	@GetMapping("/")
	public String index() {
				return "index";
	}

	@GetMapping("/allBooks")
	public String allBooks(Model model) {
		model.addAttribute("all", service.showAllBooks());
		return "viewbooks";
	}

	@GetMapping("/{id}/returnBook")
	public String returnBook(@PathVariable("id") Long id) {
		service.changeBookStatus(id, BookStatus.AVAILABLE);
		return "redirect:/allBooks";
	}

	@GetMapping("/{id}/reserveBook")
	public String reserveBook(@PathVariable("id") Long id) {
		service.changeBookStatus(id, BookStatus.RESERVED);
		return "redirect:/allBooks";
	}

	@GetMapping("/newbookform")
	public String newBookForm(@ModelAttribute("book") Book book){
		return "newbookform";
	}

	@PostMapping("/addnewbook")
	public String addNewBook(@ModelAttribute("book") Book book, Model model)
	{
		String procedureStatus = service.addNewBook(book).toString();
		model.addAttribute("statusMessage", procedureStatus);
		return "addnewbookresult";
	}

	@GetMapping("/findbyidform")
	public String findByIdForm(@ModelAttribute("book") Book book){
		return "findbyidform";
	}

	@PostMapping("/findbyid")
	public String findById(@ModelAttribute("book") Book book, Model model)
	{
		List <Book> listOfBooks = service.findBookById(book.getId());
		model.addAttribute("all", listOfBooks);
		return "viewbooks";
	}

    @GetMapping("/findbytitleform")
    public String findByTitleForm(@ModelAttribute("book") Book book){
        return "findbytitleform";
    }

    @PostMapping("/findbytitle")
    public String findByTitle(@ModelAttribute("book") Book book, Model model)
    {
        List<Book> listOfBooks = service.findBookByTitle(book.getTitle());
        model.addAttribute("all", listOfBooks);
        return "viewbooks";
    }
}