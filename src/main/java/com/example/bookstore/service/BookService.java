package com.example.bookstore.service;

import com.example.bookstore.dao.model.Book;
import com.example.bookstore.dto.BookDto;
import com.example.bookstore.exception.ResourceNotFoundException;

import java.util.List;

public interface BookService {

    List<Book> list();

    Book create(Book book);

    Book delete(Integer bookId) throws ResourceNotFoundException;

    Book update(Book book, Integer bookId) throws ResourceNotFoundException;

    Book get(Integer bookId) throws ResourceNotFoundException;

    List<Book> getBooksByGenre(Integer genreId) throws ResourceNotFoundException;

    List<Book> getBooksByAuthor(Integer authorId) throws ResourceNotFoundException;
}
