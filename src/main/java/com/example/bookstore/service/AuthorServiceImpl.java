package com.example.bookstore.service;

import com.example.bookstore.dao.model.Author;
import com.example.bookstore.dao.repository.AuthorRepository;
import com.example.bookstore.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> list() {
        return authorRepository.findAll();
    }

    @Override
    public Author create(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author get(Integer id) throws ResourceNotFoundException {
        return authorRepository.findById(id)
                .orElseThrow(()-> authorNotFound(id));
    }

    @Override
    public Author update(Author updated, Integer id) throws ResourceNotFoundException {
        Author author = authorRepository.findById(id)
                .orElseThrow(()->authorNotFound(id));
        author.setName(updated.getName());
        return authorRepository.save(author);
    }

    @Override
    public Author delete(Integer id) throws ResourceNotFoundException {
        Author author = authorRepository.findById(id)
                .orElseThrow(()->authorNotFound(id));
        authorRepository.delete(author);
        return author;
    }

    static ResourceNotFoundException authorNotFound(Integer id){
        return new ResourceNotFoundException("Author with id = "+ id+ "NOT_FOUND");
    }
}
