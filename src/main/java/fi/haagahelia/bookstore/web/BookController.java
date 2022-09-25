package fi.haagahelia.bookstore.web;

import fi.haagahelia.bookstore.domain.Book;
import fi.haagahelia.bookstore.domain.BookRepository;
import fi.haagahelia.bookstore.domain.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BookController {
    @Autowired
    private BookRepository brepository;
    @Autowired
    private CategoryRepository crepository;

    @RequestMapping(value = "/booklist")
    public String bookList(Model model) {
        model.addAttribute("books", brepository.findAll());
        return "bookList";
    }

    @RequestMapping(value = "/add")
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("categories", crepository.findAll());
        return "addBook";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Book book) {
        brepository.save(book);
        return "redirect:booklist";
    }

    @RequestMapping(value = "/delete/{isbn}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("isbn") String bookISBN, Model model) {
        brepository.deleteById(bookISBN);
        return "redirect:../booklist";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editBook(@RequestParam(name="isbn", required = false) String isbn, Model model) {
        if (brepository.findById(isbn).isPresent()) {
            model.addAttribute("book", brepository.findById(isbn));
            model.addAttribute("categories", crepository.findAll());
            return "editbook";
        }
        return "redirect:../booklist";
    }
}
