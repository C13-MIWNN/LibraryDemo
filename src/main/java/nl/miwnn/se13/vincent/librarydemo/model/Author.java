package nl.miwnn.se13.vincent.librarydemo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * @author Vincent Velthuizen
 * Someone who writes books
 **/

@SuppressWarnings("unused")
@Entity
public class Author {

    @Id @GeneratedValue
    private Long authorId;

    private String name;

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
}
