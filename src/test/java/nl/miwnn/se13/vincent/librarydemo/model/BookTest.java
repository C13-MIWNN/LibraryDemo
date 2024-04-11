package nl.miwnn.se13.vincent.librarydemo.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Vincent Velthuizen
 * Purpose for the class
 **/
class BookTest {

    @Test
    @DisplayName("Available copies for book without copies")
    void availableCopiesForBookWithoutCopies() {
        // Arrange
        Book book = new Book();
        int expectedNoCopies = 0;

        // Act
        int actualNoCopies = book.getNumberOfAvailableCopies();

        // Assert
        assertEquals(expectedNoCopies, actualNoCopies);
    }

    @Test
    @DisplayName("Available copies when all are unavailable")
    void availableCopiesWhenAllAreUnavailable() {
        // Arrange
        Book book = new Book();

        Set<Copy> copies = new HashSet<>();

        copies.add(getCopy(book, false));
        copies.add(getCopy(book, false));
        copies.add(getCopy(book, false));

        book.setCopies(copies);

        int expectedNoCopies = 0;

        // Act
        int actualNoCopies = book.getNumberOfAvailableCopies();

        // Assert
        assertEquals(expectedNoCopies, actualNoCopies);
    }

    @Test
    @DisplayName("Available copies are all available")
    void availableCopiesAreAllAvailable() {
        // Arrange
        Book book = new Book();

        Set<Copy> copies = new HashSet<>();

        copies.add(getCopy(book, true));
        copies.add(getCopy(book, true));
        copies.add(getCopy(book, true));

        book.setCopies(copies);

        int expectedNoCopies = 3;

        // Act
        int actualNoCopies = book.getNumberOfAvailableCopies();

        // Assert
        assertEquals(expectedNoCopies, actualNoCopies);
    }

    @Test
    @DisplayName("Available copies, some are available")
    void availableCopiesSomeAreAvailable() {
        // Arrange
        Book book = new Book();

        Set<Copy> copies = new HashSet<>();

        copies.add(getCopy(book, true));
        copies.add(getCopy(book, false));
        copies.add(getCopy(book, true));

        book.setCopies(copies);

        int expectedNoCopies = 2;

        // Act
        int actualNoCopies = book.getNumberOfAvailableCopies();

        // Assert
        assertEquals(expectedNoCopies, actualNoCopies);
    }

    private static Copy getCopy(Book book, boolean available) {
        Copy copy = new Copy();
        copy.setBook(book);
        copy.setAvailable(available);
        return copy;
    }
}