package gb.spring.library.api;

import lombok.Data;

@Data
public class BookRequest {
    private long bookId;
    private String name;
}
