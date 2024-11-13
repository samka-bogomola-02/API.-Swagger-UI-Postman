package ka.irissin.apipostmanspringdemo.repositories;

import ka.irissin.apipostmanspringdemo.model.BookCover;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookCoverRepository extends JpaRepository<BookCover, Long> {
    Optional<BookCover> findByBookId(Long BookId);
}
