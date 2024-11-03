package ka.irissin.apipostmanspringdemo.repositories;

import ka.irissin.apipostmanspringdemo.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
