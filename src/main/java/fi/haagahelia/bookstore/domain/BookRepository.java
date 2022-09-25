package fi.haagahelia.bookstore.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, String> {
    Book findBooksByIsbn(String isbn);
}
