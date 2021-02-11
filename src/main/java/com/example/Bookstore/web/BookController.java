package com.example.Bookstore.web;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BookController {

	@Autowired
	private BookRepository repository;

	@RequestMapping("/bookstore")
	public String index() {
		return "This is the main page :)";
	}

	@RequestMapping(value = "/booklist", method = RequestMethod.GET)
	public String BookList(Model model) {
		model.addAttribute("books", repository.findAll());

		return "booklist";
	}

	@RequestMapping(value = "/add")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		return "addbook";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Book book) {
		repository.save(book);
		return "redirect:/booklist";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long BookId, Model model) {
		repository.deleteById(BookId);
		return "redirect:../booklist";
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String EditBook(@PathVariable("id") Long BookId, Model model) {
	Optional <Book> book = repository.findById(BookId);
	model.addAttribute("book", book);
	System.out.println("ollaan editissä");
		return "editbook";
	}
	
}
