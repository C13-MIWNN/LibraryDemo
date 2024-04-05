package nl.miwnn.se13.vincent.librarydemo.controller;

import nl.miwnn.se13.vincent.librarydemo.model.Book;
import nl.miwnn.se13.vincent.librarydemo.repositories.AuthorRepository;
import nl.miwnn.se13.vincent.librarydemo.repositories.BookRepository;
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
 * Handle all requests regarding books
 **/

@SuppressWarnings("SameReturnValue")
@Controller
public class BookController {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BookController(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }


    @GetMapping({"/", "/book"})
    private String showBookOverview(Model model) {
        model.addAttribute("allBooks", bookRepository.findAll());

        return "bookOverview";
    }

    @GetMapping("/book/new")
    private String showBookForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("allAuthors", authorRepository.findAll());

        return "bookForm";
    }

    @PostMapping("/book/new")
    private String saveBook(@ModelAttribute("book") Book bookToBeSaved, BindingResult result) {
        if (bookToBeSaved.getBookId() == null
                && bookRepository.findByTitle(bookToBeSaved.getTitle()).isPresent()) {
            return "redirect:/book/new";
        }

        if (!result.hasErrors()) {
            bookRepository.save(bookToBeSaved);
        }

        return "redirect:/";
    }

    @GetMapping("/book/edit/{title}")
    private String showEditBookForm(@PathVariable("title") String title, Model model) {
        Optional<Book> book = bookRepository.findByTitle(title);

        if (book.isEmpty()) {
            return "redirect:/";
        }

        model.addAttribute("book", book.get());
        model.addAttribute("allAuthors", authorRepository.findAll());

        return "bookForm";
    }

    @GetMapping("/book/detail/{title}")
    private String showBookDetails(@PathVariable("title") String title, Model model) {
        Optional<Book> book = bookRepository.findByTitle(title);

        if (book.isEmpty()) {
            return "redirect:/book";
        }

        model.addAttribute("bookToBeShown", book.get());
        return "bookDetail";
    }
}
