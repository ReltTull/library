package gb.spring.library.api;

import gb.spring.library.model.Book;
import gb.spring.library.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService service;

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
}
