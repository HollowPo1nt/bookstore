package com.example.bookstore.service;

import com.example.bookstore.dao.model.Genre;
import com.example.bookstore.dao.repository.GenreRepository;
import com.example.bookstore.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImp implements GenreService {

    private final GenreRepository genreRepository;

    @Autowired
    public GenreServiceImp(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public List<Genre> list() {
        return genreRepository.findAll();
    }

    @Override
    public Genre create(Genre genre) {
        if (genreRepository.existsByName(genre.getName())) {
            return genre;
        } else
            return genreRepository.save(genre);
    }

    @Override
    public Genre get(Integer id) throws ResourceNotFoundException {
        return genreRepository.findById(id)
                .orElseThrow(() -> genreNotFound(id));
    }

    @Override
    public Genre update(Genre updated, Integer id) throws ResourceNotFoundException {
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> genreNotFound(id));
        if (genreRepository.existsByName(updated.getName())) {
            return genre;
        } else {
            genre.setName(updated.getName());
            return genreRepository.save(genre);
        }
    }

    @Override
    public Genre delete(Integer id) throws ResourceNotFoundException {
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> genreNotFound(id));
        genreRepository.delete(genre);
        return genre;
    }

    static ResourceNotFoundException genreNotFound(Integer id) {
        return new ResourceNotFoundException("Genre with id = " + id + "NOT_FOUND");
    }
}
