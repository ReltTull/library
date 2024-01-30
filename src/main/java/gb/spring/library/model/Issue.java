package gb.spring.library.model;

import lombok.Data;

import java.time.LocalDateTime;

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
    private final LocalDateTime issueTime;

    /**
     * Дата возврата
     */
    private final LocalDateTime refundTime;

    public Issue(long bookId, long readerId) {
        this.id = sequence++;
        this.bookId = bookId;
        this.readerId = readerId;
        this.issueTime = LocalDateTime.now();
        this.isReturned = false;
        this.refundTime = null;
    }

}
