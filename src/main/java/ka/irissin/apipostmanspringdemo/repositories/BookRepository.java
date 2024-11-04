package ka.irissin.apipostmanspringdemo.repositories;

import ka.irissin.apipostmanspringdemo.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByNameIgnoreCase(String name);
    Collection<Book> findByAuthorContainsIgnoreCase(String author);
    Collection<Book> findByNameContainsIgnoreCase(String part);
}
