package nl.miwnn.se13.vincent.librarydemo.controller;

import nl.miwnn.se13.vincent.librarydemo.model.Author;
import nl.miwnn.se13.vincent.librarydemo.model.Book;
import nl.miwnn.se13.vincent.librarydemo.model.Copy;
import nl.miwnn.se13.vincent.librarydemo.repositories.AuthorRepository;
import nl.miwnn.se13.vincent.librarydemo.repositories.BookRepository;
import nl.miwnn.se13.vincent.librarydemo.repositories.CopyRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Vincent Velthuizen
 * Set some intial data in the database for testing purposes
 **/
@Controller
public class IntializeController {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final CopyRepository copyRepository;

    public IntializeController(AuthorRepository authorRepository,
                               BookRepository bookRepository,
                               CopyRepository copyRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.copyRepository = copyRepository;
    }

    @GetMapping("/intialize")
    private String intializeDB() {

        Author patrick = makeAuthor("Patrick Rothfuss");
        Author brandon = makeAuthor("Brandon Sanderson");
        Author tolkien = makeAuthor("J.R.R. Tolkien");

        Book hobbit = makeBook("The Hobbit", tolkien);
        Book lotr = makeBook("The Lord of the Rings", tolkien);
        Book mistborn = makeBook("Mistborn", brandon);
        Book wind = makeBook("The Name of the Wind", patrick);

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
}
