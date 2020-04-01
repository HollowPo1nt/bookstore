package com.example.bookstore.service;

import com.example.bookstore.dao.model.Author;
import com.example.bookstore.exception.ResourceNotFoundException;

import java.util.List;

public interface AuthorService {

    List<Author> list();

    Author create(Author author);

    Author get(Integer id) throws ResourceNotFoundException;

    Author update(Author author, Integer id) throws ResourceNotFoundException;

    Author delete(Integer id) throws ResourceNotFoundException;

}
