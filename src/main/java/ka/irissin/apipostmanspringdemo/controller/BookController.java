package ka.irissin.apipostmanspringdemo.controller;

import ka.irissin.apipostmanspringdemo.model.Book;
import ka.irissin.apipostmanspringdemo.model.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("books")
public class BookController {

    private final BookService bookService;
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @GetMapping("{id}")
    public ResponseEntity<Book> getBookInfo(@PathVariable Long id) {
        Book book = bookService.findBook(id);
        if (book == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(book);
    }
    @GetMapping
    public ResponseEntity<Collection<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());

    }
    @PostMapping
    public Book addBook(Book book){
        return bookService.addBook(book);
    }
    @PutMapping
    public ResponseEntity<Book> editBook(Book book){
        Book book1 = bookService.editBook(book);
        if (book1 == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(book1);
    }
    @DeleteMapping("{id}")
    public Book deleteBook(@PathVariable Long id) {
        return bookService.deleteBook(id);
    }
}
