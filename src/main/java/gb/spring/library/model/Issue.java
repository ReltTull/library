package gb.spring.library.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class Issue {

    public static long sequence = 1L;

    private final long id;
    private final long bookId;
    private final long readerId;

    private boolean isReturned;

    /**
     * Дата выдачи
     */
    private final LocalDateTime timestamp;

    public Issue(long bookId, long readerId) {
        this.id = sequence++;
        this.bookId = bookId;
        this.readerId = readerId;
        this.timestamp = LocalDateTime.now();
        this.isReturned = false;
    }
}
