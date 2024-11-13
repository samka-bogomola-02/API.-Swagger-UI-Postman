package ka.irissin.apipostmanspringdemo.controller;

import jakarta.servlet.http.HttpServletResponse;
import ka.irissin.apipostmanspringdemo.model.Book;
import ka.irissin.apipostmanspringdemo.model.BookCover;
import ka.irissin.apipostmanspringdemo.service.BookCoverService;
import ka.irissin.apipostmanspringdemo.service.BookService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;

@RestController
@RequestMapping("books")
public class BookController {

    private final BookService bookService;
    private final BookCoverService bookCoverService;

    public BookController(BookService bookService, BookCoverService bookCoverService) {
        this.bookService = bookService;
        this.bookCoverService = bookCoverService;
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
    public ResponseEntity findBooks(@RequestParam(required = false) String name,
                                    @RequestParam(required = false) String author,
                                    @RequestParam(required = false) String namePart) {
        if (name != null && !name.isBlank()) {
            return ResponseEntity.ok(bookService.findByName(name));
        }
        if (author != null && !author.isBlank()) {
            return ResponseEntity.ok(bookService.findByAuthor(author));
        }
        if (namePart != null && !namePart.isBlank()) {
            return ResponseEntity.ok(bookService.findByNamePart(namePart));
        }
        return ResponseEntity.ok(bookService.getAllBooks());

    }

    @PostMapping
    public Book addBook(Book book) {
        return bookService.addBook(book);
    }

    @PutMapping
    public ResponseEntity<Book> editBook(Book book) {
        Book book1 = bookService.editBook(book);
        if (book1 == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(book1);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/{id}/cover", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> addCover(@PathVariable Long id,
                                           @RequestParam MultipartFile cover) throws IOException {
        if (cover.getSize() >= 1024 * 300) {
            return ResponseEntity.badRequest().body("Cover file size is too large.");
        }
        bookCoverService.additionCover(id, cover);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{id}/preview")
    public ResponseEntity<byte[]> downloadCover(@PathVariable Long id) {
        BookCover bookCover = bookCoverService.findBookCover(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(bookCover.getMediaType()));
        headers.setContentLength(bookCover.getPreview().length);

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(bookCover.getPreview());
    }

    @GetMapping(value = "/{id}/cover")
    public void downloadCover(@PathVariable Long id, HttpServletResponse response) throws IOException{
        BookCover bookCover = bookCoverService.findBookCover(id);
        Path path = Path.of(bookCover.getFilePath());

        try (InputStream is = Files.newInputStream(path);
            OutputStream os = response.getOutputStream();) {
            response.setContentType(bookCover.getMediaType());
            response.setContentLength((int) bookCover.getFileSize());
            is.transferTo(os);
        }
    }
}
