package nl.miwnn.se13.vincent.librarydemo.controller;

import nl.miwnn.se13.vincent.librarydemo.model.Book;
import nl.miwnn.se13.vincent.librarydemo.repositories.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vincent Velthuizen
 * Handle all requests regarding books
 **/

@Controller
public class BookController {
    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @GetMapping("/")
    private String showBookOverview(Model model) {
        model.addAttribute("allBooks", bookRepository.findAll());

        return "bookOverview";
    }
}
