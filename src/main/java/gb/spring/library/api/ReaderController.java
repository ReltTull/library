package gb.spring.library.api;

import gb.spring.library.model.Book;
import gb.spring.library.model.Reader;
import gb.spring.library.service.ReaderService;
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
@RequestMapping("/reader")
public class ReaderController {

    @Autowired
    private ReaderService service;

    @GetMapping("/ui/readers")
    public String table(Model model) {
        List<Reader> readers = service.getRepository().getReaders();
        model.addAttribute("readers", readers);
        return "ReaderTable";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Reader getReaderById (@PathVariable long id) {
        return service.getRepository().getReaderById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteReader(@PathVariable("id") long id) {
        service.deleteReader(id);
    }

    @PostMapping("/addBook")
    public ResponseEntity<Reader> addReader(@RequestBody ReaderRequest request) {
        final Reader reader;
        try {
            reader = service.reader(request);
        } catch (NumberFormatException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(reader);
    }
}
