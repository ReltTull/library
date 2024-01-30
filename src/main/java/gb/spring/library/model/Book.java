package gb.spring.library.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Book {
    public static long sequence = 1L;

    private final long id;
    private final String name;
    public Book(String name) {
        this(sequence++, name);
    }
}
