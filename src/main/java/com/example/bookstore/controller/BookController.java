package com.example.bookstore.controller;

import com.example.bookstore.dao.model.Author;
import com.example.bookstore.dao.model.Book;
import com.example.bookstore.dao.model.Genre;
import com.example.bookstore.dao.model.Order;
import com.example.bookstore.exception.ResourceNotFoundException;
import com.example.bookstore.service.AuthorService;
import com.example.bookstore.service.BookService;
import com.example.bookstore.service.GenreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    @Autowired
    public BookController(BookService bookService, AuthorService authorService, GenreService genreService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @GetMapping("/books")
    public String getBooks(Model model) {
        List<Book> list = bookService.list();
        log.info("Get books: {}", list);
        model.addAttribute("books", list);
        return "books";
    }

    @GetMapping("/books/add")
    public String addBookPage(Model model) throws ResourceNotFoundException {
        List<Genre> genres = genreService.list();
        List<Author> authors = authorService.list();
        model.addAttribute("authors", authors);
        model.addAttribute("genres", genres);
        model.addAttribute("book", new Book());
        log.info("Get add_book page");
        return "add_book";
    }

    @PostMapping("/books/add")
    public String addBook(@Valid Book book) {
        bookService.create(book);
        log.info("Post book: {}", book);
        return "redirect:/books";
    }

    @GetMapping("/books/{bookId}")
    public String newOrder(@PathVariable Integer bookId, Model model) throws ResourceNotFoundException {
        Book book = bookService.get(bookId);
        model.addAttribute("book", book);
        log.info("Get book: {}", book);
        return "book_info";
    }

    @GetMapping("/books/byAuthor/")
    public String booksByAuthor(@RequestParam Integer authorId, Model model) throws ResourceNotFoundException {
        List<Book> books = bookService.getBooksByAuthor(authorId);
        model.addAttribute("books", books);
        log.info("Get books {} by authorId {}", books, authorId);
        return "books";
    }

    @GetMapping("/books/byGenre/")
    public String booksByGenre(@RequestParam Integer genreId, Model model) throws ResourceNotFoundException {
        List<Book> books = bookService.getBooksByGenre(genreId);
        model.addAttribute("books", books);
        log.info("Get books {} by genreId {}", books, genreId);
        return "books";
    }

    @GetMapping("/books/order")
    public String getBookOrderPage(@RequestParam Integer bookId, Model model) throws ResourceNotFoundException {
        Book book = bookService.get(bookId);
        Order order = new Order();
        order.setBook(book);
        List<Genre> genres = book.getGenres();
        List<Author> authors = book.getAuthors();
        model.addAttribute("order", order);
        model.addAttribute("book", book);
        model.addAttribute("genres", genres);
        model.addAttribute("authors", authors);
        log.info("Get orderPage for book: {}", book);
        return "book_order";
    }
}
