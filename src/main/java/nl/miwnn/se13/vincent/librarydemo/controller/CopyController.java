package nl.miwnn.se13.vincent.librarydemo.controller;

import nl.miwnn.se13.vincent.librarydemo.model.Book;
import nl.miwnn.se13.vincent.librarydemo.model.Copy;
import nl.miwnn.se13.vincent.librarydemo.repositories.BookRepository;
import nl.miwnn.se13.vincent.librarydemo.repositories.CopyRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

/**
 * @author Vincent Velthuizen
 * Purpose for the class
 **/

@Controller
public class CopyController {
    private final BookRepository bookRepository;
    private final CopyRepository copyRepository;


    public CopyController(BookRepository bookRepository, CopyRepository copyRepository) {
        this.bookRepository = bookRepository;
        this.copyRepository = copyRepository;
    }

    @GetMapping("/copy/new/{bookTitle}")
    private String createNewCopy(@PathVariable("bookTitle") String bookTitle) {
        Optional<Book> optionalBook = bookRepository.findByTitle(bookTitle);

        if (optionalBook.isPresent()) {
            Copy copy = new Copy();
            copy.setBook(optionalBook.get());
            copyRepository.save(copy);
        }

        return "redirect:/";
    }

    @GetMapping("/copy/borrow/{copyId}")
    private String makeCopyUnavailable(@PathVariable("copyId") Long copyId) {
        return setAvailabilityForCopyAndRedirectToBook(copyId, false);
    }

    @GetMapping("/copy/return/{copyId}")
    private String makeCopyAvailable(@PathVariable("copyId") Long copyId) {
        return setAvailabilityForCopyAndRedirectToBook(copyId, true);
    }

    private String setAvailabilityForCopyAndRedirectToBook(Long copyId, boolean available) {
        Optional<Copy> optionalCopy = copyRepository.findById(copyId);

        if (optionalCopy.isEmpty()) {
            return "redirect:/book/overview";
        }

        Copy copy = optionalCopy.get();
        copy.setAvailable(available);
        copyRepository.save(copy);

        return String.format("redirect:/book/detail/%s", copy.getBook().getTitle());
    }
}
