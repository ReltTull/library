package gb.spring.library.repository;

import gb.spring.library.model.Issue;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class IssueRepository {
    private final List<Issue> issues;

    public List<Issue> getIssues() {
        return List.copyOf(issues);
    }

    public IssueRepository() {
        this.issues = new ArrayList<>();
    }

    public void save(Issue issue) {
        // insert into ....
        issues.add(issue);
    }

    public Issue getIssueById(long id) {
        return issues.stream()
                .filter(issue -> issue.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void returnBook(long id) {
        issues.stream()
                .filter(issue -> issue.getId() == id)
                .forEach(issue -> issue.setReturned(true));
    }
}
