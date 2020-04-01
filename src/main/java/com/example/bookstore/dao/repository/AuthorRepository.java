package com.example.bookstore.dao.repository;

import com.example.bookstore.dao.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

}
