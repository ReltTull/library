package gb.spring.library.exceptions;

public class ExceedingReadersBookLimitException extends RuntimeException{

    public ExceedingReadersBookLimitException(String s) {
        super(s);
    }
}
