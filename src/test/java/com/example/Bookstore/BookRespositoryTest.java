package com.example.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.example.Bookstore.web.Book;
import com.example.Bookstore.web.BookRepository;
import com.example.Bookstore.web.Category;

import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BookRespositoryTest {

	@Autowired
	private BookRepository repository;

	@Test
	public void findByAuthorShouldRetrunBook() {
		List<Book> books = repository.findByTitle("It");

		assertThat(books).hasSize(1);
		assertThat(books.get(0).getAuthor()).isEqualTo("Stephen King");
	}

	@Test
	public void createNewBook() {
		Book book = new Book("The outsider", "Stephen King", 2018, "25656", "29,90€", new Category("Scifi"));
		repository.save(book);
		assertThat(book.getId()).isNotNull();
	}

}