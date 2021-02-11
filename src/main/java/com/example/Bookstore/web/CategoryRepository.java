package com.example.Bookstore.web;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository <Category, Long> {
	
	List<Category> findById(long id);
	
	List<Category> findByName(String name);


}
