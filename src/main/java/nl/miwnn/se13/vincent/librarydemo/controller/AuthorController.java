package nl.miwnn.se13.vincent.librarydemo.controller;

import nl.miwnn.se13.vincent.librarydemo.model.Author;
import nl.miwnn.se13.vincent.librarydemo.repositories.AuthorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

/**
 * @author Vincent Velthuizen
 * Deal with everything related to authors
 **/
@SuppressWarnings("SameReturnValue")
@Controller
public class AuthorController {
    private final AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @GetMapping("/author")
    private String showAllAuthors(Model model) {
        model.addAttribute("allAuthors", authorRepository.findAll());
        model.addAttribute("newAuthor", new Author());

        return "authorOverview";
    }

    @PostMapping("/author/new")
    private String saveOrUpdateAuthor(@ModelAttribute("newAuthor") Author author, BindingResult result) {
        if (!result.hasErrors()) {
            authorRepository.save(author);
        }

        return "redirect:/author";
    }

    @GetMapping("/author/detail/{authorName}")
    private String showAuthorDetailPage(@PathVariable("authorName") String authorName, Model model) {
        Optional<Author> author = authorRepository.findAuthorByName(authorName);

        if (author.isEmpty()) {
            return "redirect:/author";
        }

        model.addAttribute("authorToBeShown", author.get());

        return "authorDetail";
    }
}
