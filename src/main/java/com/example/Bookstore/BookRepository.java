package com.example.Bookstore;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.Bookstore.web.Book;


public interface BookRepository extends CrudRepository<Book, Long> {
	
	List<Book> findByTitle(String title);

}
