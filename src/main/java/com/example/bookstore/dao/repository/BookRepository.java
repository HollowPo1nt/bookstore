package com.example.bookstore.dao.repository;

import com.example.bookstore.dao.model.Author;
import com.example.bookstore.dao.model.Book;
import com.example.bookstore.dao.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findAllByGenres(Genre genre);

    List<Book> findAllByAuthors(Author author);
}
