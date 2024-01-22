package gb.spring.library.service;

import gb.spring.library.api.BookRequest;
import gb.spring.library.model.Book;
import gb.spring.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository repository;

    public BookRepository getRepository() {
        return repository;
    }

    public Book book(BookRequest request) {
        Book book = new Book(request.getBookId(), request.getName());
        repository.addBook(book);
        return book;
    }

    public void deleteBook(long id) {
        if (repository.getBookById(id) != null) {
            repository.deleteBook(id);
        }
        else throw new NoSuchElementException();
    }

}
