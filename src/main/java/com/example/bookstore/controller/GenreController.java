package com.example.bookstore.controller;

import com.example.bookstore.dao.model.Genre;
import com.example.bookstore.service.GenreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
public class GenreController {

    private final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("/genres")
    public String getGenres(Model model) {
        List<Genre> list = genreService.list();
        log.info("Get genres: {}", list);
        model.addAttribute("genres", list);
        return "genres";
    }

    @GetMapping("/genres/add")
    public String addGenreMethod(Model model) {
        model.addAttribute("genre", new Genre());
        return "add_genre";
    }

    @PostMapping("/genres/add")
    public String addGenre(@Valid Genre genre) {
        genreService.create(genre);
        log.info("Post genre: {}", genre);
        return "redirect:/genres";
    }
}
