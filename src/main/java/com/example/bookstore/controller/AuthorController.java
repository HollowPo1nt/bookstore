package com.example.bookstore.controller;

import com.example.bookstore.dao.model.Author;
import com.example.bookstore.dao.model.Genre;
import com.example.bookstore.service.AuthorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping
@Slf4j
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/authors")
    public String getAuthors(Model model) {
        List<Author> list = authorService.list();
        log.info("Get authors: {}", list);
        model.addAttribute("authors", list);
        return "authors";
    }

    @PostMapping("/authors/add")
    public String addAuthor(Author author) {
        authorService.create(author);
        log.info("Post author: {}", author);
        return "redirect:/authors";
    }

    @GetMapping("/authors/add")
    public String addGenreMethod(Model model) {
        model.addAttribute("author", new Author());
        log.info("Get add_author page");
        return "add_author";
    }

}
