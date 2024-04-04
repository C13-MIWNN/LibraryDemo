package nl.miwnn.se13.vincent.librarydemo.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

/**
 * @author Vincent Velthuizen
 * Represents a book that can have copies
 **/

@Entity
public class Book {
    @Id @GeneratedValue
    private Long bookId;

    @Column(unique = true)
    private String title;

    @ManyToMany
    private Set<Author> authors;

    @OneToMany(mappedBy = "book")
    private List<Copy> copies;

    public int getNumberOfAvailableCopies() {
        int count = 0;

        for (Copy copy : copies) {
            if (copy.getAvailable()) {
                count++;
            }
        }

        return count;
    }

    public int getTotalNumberOfCopies() {
        return copies.size();
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }
}
