import lt.techin.library.Author;
import lt.techin.library.Book;
import lt.techin.library.BookCatalog;
import lt.techin.library.BookNotFoundException;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class BookCatalogImpl implements BookCatalog {

    ArrayList<Book> booklist = new ArrayList<>();


    @Override
    public void addBook(Book book) {

        if (book == null) {
            throw new IllegalArgumentException();
        }
        if (book.getIsbn() == null) {
            throw new IllegalArgumentException();
        }
        if (book.getIsbn().isEmpty()) {
            throw new IllegalArgumentException();
        }
        if (book.getTitle() == null) {
            throw new IllegalArgumentException();
        }
        if (book.getTitle().isEmpty()) {
            throw new IllegalArgumentException();
        }

        if (booklist.contains(book)) {

        } else {
            booklist.add(book);
        }


    }

    @Override
    public Book getBookByIsbn(String s) {

           for (Book book : booklist) {
            if (book.getIsbn().equals(s)) {
                return book;
            }
        }
        throw new BookNotFoundException("BookNotFoundException");
    }

    @Override
    public List<Book> searchBooksByAuthor(String s) {
        List<Book> booksByAuthors = new ArrayList<>();
        for (Book book : booklist) {
            for (Author author : book.getAuthors()) {
                if (author.getName().equals(s)) {
                    booksByAuthors.add(book);
                }
            }
        }

        return booksByAuthors;
    }

    @Override
    public int getTotalNumberOfBooks() {
        return booklist.size();
    }

    @Override
    public boolean isBookInCatalog(String s) {
        for (Book book : booklist) {
            if (book.getIsbn().equals(s)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isBookAvailable(String s) {
        for (Book book : booklist) {
            if (book.isAvailable()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Book findNewestBookByPublisher(String s) {
       return  booklist
               .stream()
               .filter(b ->b.getPublisher().equals(s))
               .max(Comparator.comparing(Book::getPublicationYear))
               .orElseThrow(() -> new BookNotFoundException("BookNotFoundException"));
    }

    @Override
    public List<Book> getSortedBooks() {

        return booklist.stream()
                .sorted(Comparator.comparing(Book::getPublicationYear))
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, List<Book>> groupBooksByPublisher() {
        return booklist.stream().collect(groupingBy(Book::getPublisher));

    }

    @Override
    public List<Book> filterBooks(Predicate<Book> predicate) {

        return booklist.stream().filter(predicate).toList();
    }

    @Override
    public BigDecimal calculateTotalPrice() {

        return booklist.stream()
                .map(Book::getPrice).
                reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
