package info.kunalgohrani.courseportal.exception;

public class AuthorNotFoundException extends RuntimeException {
    private String msg;

    public AuthorNotFoundException() {
    }

    public AuthorNotFoundException(String msg) {
        super(msg);
        this.msg = msg;
    }
}
