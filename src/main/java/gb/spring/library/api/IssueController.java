package gb.spring.library.api;

import gb.spring.library.model.Issue;
import gb.spring.library.service.IssueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequestMapping("/issue")
public class IssueController {
    @Autowired
    private IssueService service;

  @PutMapping
  public void returnBook(long issueId) {
      service.returnBook(issueId);
  }

    @PostMapping
    public ResponseEntity<Issue> issueBook(@RequestBody IssueRequest request) {
        log.info("Получен запрос на выдачу: readerId = {}, bookId = {}", request.getReaderId(), request.getBookId());

        final Issue issue;
        try {
            issue = service.issue(request);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.CONFLICT).body(issue);
    }
    @GetMapping("/{id}")
    @ResponseBody
    public Issue getIssueInfo(@PathVariable long id) {
      if (service.getIssueRepository().getIssueById(id) != null) {
          return service.getIssueRepository().getIssueById(id);
      }
      else return null;
    }

}
