package nl.miwnn.se13.vincent.librarydemo.controller;

import nl.miwnn.se13.vincent.librarydemo.model.Author;
import nl.miwnn.se13.vincent.librarydemo.model.Book;
import nl.miwnn.se13.vincent.librarydemo.model.Copy;
import nl.miwnn.se13.vincent.librarydemo.model.LibraryUser;
import nl.miwnn.se13.vincent.librarydemo.repositories.AuthorRepository;
import nl.miwnn.se13.vincent.librarydemo.repositories.BookRepository;
import nl.miwnn.se13.vincent.librarydemo.repositories.CopyRepository;
import nl.miwnn.se13.vincent.librarydemo.services.LibraryUserService;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Vincent Velthuizen
 * Set some intial data in the database for testing purposes
 **/
@SuppressWarnings("SameReturnValue")
@Controller
public class InitializeController {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final CopyRepository copyRepository;

    private final LibraryUserService libraryUserService;

    public InitializeController(AuthorRepository authorRepository,
                                BookRepository bookRepository,
                                CopyRepository copyRepository,
                                LibraryUserService libraryUserService) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.copyRepository = copyRepository;
        this.libraryUserService = libraryUserService;
    }

    @EventListener
    private void seed(ContextRefreshedEvent event) {
        if (libraryUserService.isNotInitialised()) {
            initializeDB();
        }
    }

    @GetMapping("/initialize")
    private String initializeDB() {
        makeUser("Vincent", "Vincent");

        Author patrick = makeAuthor("Patrick Rothfuss");
        Author brandon = makeAuthor("Brandon Sanderson");
        Author tolkien = makeAuthor("J.R.R. Tolkien");

        Book hobbit = makeBook("The Hobbit", tolkien);
        Book lotr = makeBook("The Lord of the Rings", tolkien);
        Book mistborn = makeBook("Mistborn", brandon);
        makeBook("The Name of the Wind", patrick);

        makeCopy(hobbit);
        makeCopy(lotr);
        makeCopy(lotr).setAvailable(false);
        makeCopy(lotr).setAvailable(false);
        makeCopy(mistborn);

        return "redirect:/";
    }

    private Author makeAuthor(String name) {
        Author author = new Author();
        author.setName(name);
        authorRepository.save(author);
        return author;
    }

    private Book makeBook(String title, Author author) {
        Book book = new Book();
        book.setTitle(title);

        Set<Author> authorSet = new HashSet<>();
        authorSet.add(author);
        book.setAuthors(authorSet);

        bookRepository.save(book);
        return book;
    }

    private Copy makeCopy(Book book) {
        Copy copy = new Copy();
        copy.setBook(book);
        copyRepository.save(copy);
        return copy;
    }

    private LibraryUser makeUser(String username, String password) {
        LibraryUser user = new LibraryUser();
        user.setUsername(username);
        user.setPassword(password);
        libraryUserService.saveUser(user);
        return user;
    }
}
