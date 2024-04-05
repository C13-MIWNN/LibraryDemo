package nl.miwnn.se13.vincent.librarydemo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

/**
 * @author Vincent Velthuizen
 * A copy of a book that is either on the shelf or lent to a customer
 **/

@SuppressWarnings("unused")
@Entity
public class Copy {
    @Id @GeneratedValue
    private Long copyId;

    private Boolean available = true;

    @ManyToOne
    private Book book;

    public Long getCopyId() {
        return copyId;
    }

    public void setCopyId(Long copyId) {
        this.copyId = copyId;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
