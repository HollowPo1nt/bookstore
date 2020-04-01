package com.example.bookstore.service;

import com.example.bookstore.dao.model.Genre;
import com.example.bookstore.exception.ResourceNotFoundException;

import java.util.List;

public interface GenreService {

    List<Genre> list();

    Genre create(Genre genre);

    Genre get(Integer id) throws ResourceNotFoundException;

    Genre update(Genre genre, Integer id) throws ResourceNotFoundException;

    Genre delete(Integer id) throws ResourceNotFoundException;

}
