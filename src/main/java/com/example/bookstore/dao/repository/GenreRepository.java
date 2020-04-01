package com.example.bookstore.dao.repository;

import com.example.bookstore.dao.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Integer> {

    Boolean existsByName(String name);

}
