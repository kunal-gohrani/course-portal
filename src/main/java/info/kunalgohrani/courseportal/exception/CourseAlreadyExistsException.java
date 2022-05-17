package info.kunalgohrani.courseportal.exception;

public class CourseAlreadyExistsException extends RuntimeException {
    private String msg;

    public CourseAlreadyExistsException() {
    }

    public CourseAlreadyExistsException(String msg) {
        super(msg);
        this.msg = msg;
    }
}
