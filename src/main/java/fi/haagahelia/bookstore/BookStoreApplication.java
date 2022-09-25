package fi.haagahelia.bookstore;

import fi.haagahelia.bookstore.domain.Book;
import fi.haagahelia.bookstore.domain.BookRepository;
import fi.haagahelia.bookstore.domain.Category;
import fi.haagahelia.bookstore.domain.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookStoreApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(BookRepository bookRepository, CategoryRepository categoryRepository) {
        return args -> {
            Category fantasyCat = new Category("Fantasy");
            Category romanceCat = new Category("Romance");
            Category horrorCat = new Category("Horror");
            categoryRepository.save(fantasyCat);
            categoryRepository.save(romanceCat);
            categoryRepository.save(horrorCat);

            Book book1 = new Book("Harry Potter and the Philosopher's Stone", "J.K. Rowling", 1997, "0-7475-3269-9", 8.99, fantasyCat); // 1997
            Book book2 = new Book("Harry Potter and the Chamber of Secrets", "J.K. Rowling", 1998, "0-7475-3849-2", 8.99, fantasyCat);  // 1998
            Book book3 = new Book("Harry Potter and the Prisoner of Azkaban", "J.K. Rowling", 1999, "0-7475-4215-5", 8.99, fantasyCat); // 1999
            Book book4 = new Book("The proposal", "Jasmin Guillory", 2017, "978-1-250-12503-7", 8.99, romanceCat); // 2017
            bookRepository.save(book1);
            bookRepository.save(book2);
            bookRepository.save(book3);
            bookRepository.save(book4);
        };
    }
}
