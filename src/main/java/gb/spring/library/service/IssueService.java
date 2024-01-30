package gb.spring.library.service;

import gb.spring.library.api.IssueRequest;
import gb.spring.library.exceptions.ExceedingReadersBookLimitException;
import gb.spring.library.model.Issue;
import gb.spring.library.model.Reader;
import gb.spring.library.repository.BookRepository;
import gb.spring.library.repository.IssueRepository;
import gb.spring.library.repository.ReaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.core.env.Environment;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
//@ImportResource("classpath:application.yml")
public class IssueService {
    private final IssueRepository issueRepository;
    private final BookRepository bookRepository;
    private final ReaderRepository readerRepository;
    /**
     * Использование значения, заданного в файле .yml
     */
    @Value("${application.max-allowed-books}")
    private String maxAllowedBooks;

    public Issue issue(IssueRequest request) {
        if (bookRepository.getBookById(request.getBookId()) == null) {
            throw new NoSuchElementException("Не найдена книга с идентификатором \"" + request.getBookId() + "\"");
        }
        if (readerRepository.getReaderById(request.getReaderId()) == null) {
            throw new NoSuchElementException("Не найден читатель с идентификатором \"" + request.getReaderId() + "\"");
        }
        // можно проверить, что у читателя нет книг на руках (или его лимит не превышает в Х книг)
        if (!isBookLimit(readerRepository.getReaderById(request.getReaderId()))) {
            throw new ExceedingReadersBookLimitException("Reader's book limit is excess!");
        }
        Issue issue = new Issue(request.getBookId(), request.getReaderId());
        issueRepository.save(issue);
        return issue;
    }
    public IssueRepository getRepository () {
        return issueRepository;
    }
    @GetMapping("/{id}")
    @ResponseBody
    public Issue getIssueById(@PathVariable long id) {
        return issueRepository.getIssueById(id);
    }


    public String getBookNameById(long id){
        return bookRepository.getBookById(id).getName();
    }
    public String getReaderNameById(long id) {
        return readerRepository.getReaderById(id).getName();
    }
    public void returnBook(long id) {
        issueRepository.returnBook(id);
    }

    /**
     * Проверка, не превышен ли лимит выданных книг у читателя. Ищет Issue, где idReader совпадает с искомым и
     * добавляет в отдельный лист, с размером которого сравнивается число допустимых книг на руках.
     * @param reader
     * @return
     */
    public boolean isBookLimit(Reader reader) {
        int lim = 0;
        List<Issue> i = issueRepository.getIssues().stream()
                .filter(issue -> issue.getReaderId() == reader.getId()).toList();
        return i.size() > Integer.parseInt(maxAllowedBooks);
    }
}
