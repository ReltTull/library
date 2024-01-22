package gb.spring.library.service;

import gb.spring.library.api.ReaderRequest;
import gb.spring.library.model.Issue;
import gb.spring.library.model.Reader;
import gb.spring.library.repository.IssueRepository;
import gb.spring.library.repository.ReaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReaderService {
    private final ReaderRepository repository;
    private final IssueRepository issueRepository;

    public ReaderRepository getRepository() {return repository;}

    public Reader reader(ReaderRequest request) {
        Reader reader = new Reader(request.getId(), request.getName());
        repository.addReader(reader);
        return reader;
    }

    public void deleteReader(long id) {
        if (repository.getReaderById(id) != null) {
            repository.deleteReader(id);
        }
        else throw new NoSuchElementException();
    }

    public List<Issue> getIssuesByReader (long id) {
        if (repository.getReaderById(id) != null) {
            return issueRepository.getIssues().stream()
                    .filter(issue -> issue.getReaderId() == repository.getReaderById(id).getId()).toList();
        }
        return null;
    }
}
