package com.example.Bookstore.web;


import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class BookController {

	@Autowired
	private BookRepository repository;
	
	@Autowired 
	private CategoryRepository crepository;
	
    @RequestMapping(value="/login")
    public String login() {	
        return "login";
    }

	@RequestMapping("/bookstore")
	public String index() {
		return "This is the main page :)";
	}

	@RequestMapping(value = "/booklist", method = RequestMethod.GET)
	public String BookList(Model model) {
		model.addAttribute("books", repository.findAll());
		return "booklist";
	}
	
	// RESTful service to get all books
    @RequestMapping(value="/books", method = RequestMethod.GET)
    public @ResponseBody List<Book> bookListRest() {	
        return (List<Book>) repository.findAll();
    }    

	// RESTful service to get books by id
    @RequestMapping(value="/book/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long BookId) {	
    	return repository.findById(BookId);
    } 

	@RequestMapping(value = "/add")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categorys", crepository.findAll());
		return "addbook";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Book book) {
		repository.save(book);
		return "redirect:/booklist";
	}

	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String EditBook(@PathVariable("id") Long BookId, Model model) {
	Optional <Book> book = repository.findById(BookId);
	model.addAttribute("book", book);
	model.addAttribute("categorys", crepository.findAll());

		return "editbook";
	}
	
	  @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	    @PreAuthorize("hasAuthority('ADMIN')")
	    public String deleteBook(@PathVariable("id") Long BookId, Model model) {
	    	repository.deleteById(BookId);
	        return "redirect:../booklist";
	    } 
	
}
