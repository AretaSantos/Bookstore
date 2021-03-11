package com.example.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.awt.print.Book;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.Bookstore.web.*;

@RunWith(SpringRunner.class)

public class BookRespositoryTest {
	
	@Autowired
	private BookRepository repository;

	@Test 
	public void findByAuthorSHouldRetrunBook() {
	      List<Book> books = repository.findByTitle("It");
	        
	        assertThat(books).hasSize(1);
	        assertThat(books.get(0).getauthor()).isEqualTo("Stephen King");
	    }
	    
	    @Test
	    public void createNewBook() {
	    	Book book = new Book("The outsider", "Stephen King", 2018, "25656", "29,90€", new Category("Scifi/horror"));
	    	repository.save(book);
	    	assertThat(book.getId()).isNotNull();
	    }    

	}