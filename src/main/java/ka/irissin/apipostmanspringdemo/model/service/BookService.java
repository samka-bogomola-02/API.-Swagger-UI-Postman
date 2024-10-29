package ka.irissin.apipostmanspringdemo.model.service;

import ka.irissin.apipostmanspringdemo.model.Book;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;

@Service
public class BookService {
    // Создание хранилища для книг
    private final HashMap<Long, Book> books = new HashMap<>();
    private long lastId = 0;

    // Добавление новой книги
    public Book addBook(Book book) {
        book.setId((int) ++lastId);
        books.put(lastId, book);
        return book;
    }
    // Получение книги
    public Book findBook(long id) {
        return books.get(id);
    }
    // Редактирование книги
    public Book editBook(Book book) {
        if (books.containsKey((long) book.getId())) {
        books.put((long) book.getId(), book);
        return book;
        }
        return null;
    }
    // Удаление книги
    public Book deleteBook(long id) {
        books.remove(id);
        return books.get(id); // Возвращаем удаленную книгу для удобства проверки
    }

    // Получение списка всех книг
    public Collection<Book> getAllBooks() {
        return books.values();
    }
}