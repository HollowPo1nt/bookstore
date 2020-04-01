package com.example.bookstore.service;

import com.example.bookstore.dao.model.Author;
import com.example.bookstore.dao.model.Book;
import com.example.bookstore.dao.model.Genre;
import com.example.bookstore.dao.repository.AuthorRepository;
import com.example.bookstore.dao.repository.BookRepository;
import com.example.bookstore.dao.repository.GenreRepository;
import com.example.bookstore.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.bookstore.service.AuthorServiceImpl.authorNotFound;
import static com.example.bookstore.service.GenreServiceImp.genreNotFound;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, GenreRepository genreRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
    }

    @Override
    public List<Book> list() {
        return bookRepository.findAll();
    }

    @Override
    public Book create(Book book) {
        Book newBook = new Book();
        newBook.setTitle(book.getTitle());
        newBook.setDescription(book.getDescription());
        newBook.setPrice(book.getPrice());
        newBook.setGenres(book.getGenres());
        newBook.setAuthors(book.getAuthors());
        return bookRepository.save(newBook);
    }

    @Override
    public Book delete(Integer bookId) throws ResourceNotFoundException {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> bookNotFound(bookId));
        bookRepository.delete(book);
        return book;
    }

    @Override
    public Book update(Book updated, Integer bookId) throws ResourceNotFoundException {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> bookNotFound(bookId));
        book.setTitle(updated.getTitle());
        book.setDescription(updated.getDescription());
        book.setPrice(updated.getPrice());
        book.setGenres(updated.getGenres());
        book.setAuthors(updated.getAuthors());
        return bookRepository.save(book);
    }

    @Override
    public Book get(Integer bookId) throws ResourceNotFoundException {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> bookNotFound(bookId));
    }

    @Override
    public List<Book> getBooksByGenre(Integer genreId) throws ResourceNotFoundException {
        Genre genre = genreRepository.findById(genreId)
                .orElseThrow(() -> genreNotFound(genreId));
        return bookRepository.findAllByGenres(genre);
    }

    @Override
    public List<Book> getBooksByAuthor(Integer authorId) throws ResourceNotFoundException {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> authorNotFound(authorId));
        return bookRepository.findAllByAuthors(author);
    }

    ResourceNotFoundException bookNotFound(Integer id) {
        return new ResourceNotFoundException("Book with id = " + id + "NOT_FOUND");
    }

}
