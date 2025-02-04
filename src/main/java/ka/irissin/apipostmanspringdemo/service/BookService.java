package ka.irissin.apipostmanspringdemo.service;

import ka.irissin.apipostmanspringdemo.model.Book;
import ka.irissin.apipostmanspringdemo.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Добавление новой книги
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }
    // Получение книги
    public Book findBook(long id) {
        return bookRepository.findById(id).get();
    }
    // Редактирование книги
    public Book editBook(Book book) {
        return bookRepository.save(book);
    }
    // Удаление книги
    public void deleteBook(long id) {
        bookRepository.deleteById(id);
    }

    // Получение списка всех книг
    public Collection<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    public Book findByName(String name) {
        return bookRepository.findByNameIgnoreCase(name);
    }
    public Collection<Book> findByAuthor(String author) {
        return bookRepository.findByAuthorContainsIgnoreCase(author);
    }
    public Collection<Book> findByNamePart(String part) {
        return bookRepository.findByNameContainsIgnoreCase(part);
    }
}