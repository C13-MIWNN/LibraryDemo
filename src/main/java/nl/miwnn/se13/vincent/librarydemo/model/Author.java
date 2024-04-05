package nl.miwnn.se13.vincent.librarydemo.model;

import jakarta.persistence.*;

import java.util.Set;

/**
 * @author Vincent Velthuizen
 * Someone who writes books
 **/

@SuppressWarnings("unused")
@Entity
public class Author {

    @Id @GeneratedValue
    private Long authorId;

    @Column(unique = true)
    private String name;

    @ManyToMany(mappedBy = "authors", cascade = CascadeType.ALL)
    private Set<Book> books;

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}
