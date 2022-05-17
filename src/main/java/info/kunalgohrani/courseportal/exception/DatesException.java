package info.kunalgohrani.courseportal.exception;

public class DatesException extends RuntimeException {

    private String msg;

    public DatesException() {
    }

    public DatesException(String msg) {
        super(msg);
        this.msg = msg;
    }
}
