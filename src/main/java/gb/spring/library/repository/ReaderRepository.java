package gb.spring.library.repository;

import gb.spring.library.model.Book;
import gb.spring.library.model.Reader;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class ReaderRepository {

    private final List<Reader> readers;

    public ReaderRepository() {
        this.readers = new ArrayList<>();
    }

    @PostConstruct
    public void generateData() {
        readers.addAll(List.of(
                new Reader("Lew"),
                new Reader("Nikolay"),
                new Reader("Robert")
        ));
    }

    public void addReader(Reader reader) {
        readers.add(reader);
    }

    public Reader getReaderById(long id) {
        return readers.stream().filter(it -> Objects.equals(it.getId(), id))
                .findFirst()
                .orElse(null);
    }

    public void deleteReader(long id) {
        readers.remove(id);
    }

    public List<Reader> getReaders() {
        return readers;
    }
}
