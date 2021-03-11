package com.example.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import com.example.Bookstore.web.Book;
import com.example.Bookstore.web.Category;
import com.example.Bookstore.web.BookRepository;
import com.example.Bookstore.web.CategoryRepository;
import com.example.Bookstore.web.User;
import com.example.Bookstore.web.UserRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner Bookstore(BookRepository repository, CategoryRepository crepository, UserRepository urepository) {
		return (args) -> {
			log.info("Save a few books");
			
			crepository.save(new Category("Horror"));
			crepository.save(new Category("Scifi"));
			
			urepository.deleteAll();
			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			
			urepository.save(user1);
			urepository.save(user2);
			
			
			repository.save(new Book("It", "Stephen King", 1986, "1234", "29,90€", crepository.findByName("Horror").get(0)));
			repository.save(new Book("Pet Sematary", "Stephen King", 1983, "2345", "34,90€", crepository.findByName("Scifi").get(0)));	
			
			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}

		};

}
}
