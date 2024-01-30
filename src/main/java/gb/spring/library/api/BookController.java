package gb.spring.library.api;

import gb.spring.library.model.Book;
import gb.spring.library.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
//@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService service;

    @GetMapping("/ui/books")
    public String table(Model model) {
        List<Book> books = service.getRepository().getBooks();
        model.addAttribute("books", books);
        return "BookTable";
    }

    @GetMapping("/test")
    public String test() {
        return "home";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Book getBookById (@PathVariable long id) {
        return service.getRepository().getBookById(id);
    }


    @DeleteMapping("/delete/{id}")
    public void deleteBook(@PathVariable("id") long id) {
        service.deleteBook(id);
    }

    @PostMapping("/addBook")
    public ResponseEntity<Book> addBook(@RequestBody BookRequest request) {
        final Book book;
        try {
            book = service.book(request);
        } catch (NumberFormatException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(book);
    }

//    @GetMapping("/books")
//    public String books(Model model) {
//        List<Book> books = service.getRepository().getBooks();
//        model.addAttribute("books", books);
//        return "BookTable.html";
//    }
//
//    @GetMapping("/{id}")
//    public String getUser(@PathVariable long id, Model model) {
//        model.addAttribute("books", service.getRepository().getBookById(id));
//        return "bookProfile";
//    }
}
