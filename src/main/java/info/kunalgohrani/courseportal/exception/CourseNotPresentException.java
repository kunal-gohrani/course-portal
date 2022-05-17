package info.kunalgohrani.courseportal.exception;

public class CourseNotPresentException extends RuntimeException {
    private String msg;

    public CourseNotPresentException() {
    }

    public CourseNotPresentException(String msg) {
        super(msg);
        this.msg = msg;
    }
}
