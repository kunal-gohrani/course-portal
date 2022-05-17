package info.kunalgohrani.courseportal.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = CourseNotPresentException.class)
    public ResponseEntity<Object> courseNotPresentExceptionHandler(CourseNotPresentException ex) {
        log.error("Could not find Course");
        log.error("Printing stack trace from exception handler:\n" + ex);
        Map<String,String> errorResponse = new HashMap<String,String>();
        errorResponse.put("mesage",ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value=CourseAlreadyExistsException.class)
    public ResponseEntity<Object> courseAlreadyExistsExceptionHandler(CourseAlreadyExistsException ex){
        log.error("Course already Exists!\n Exception: \n"+ ex);
        Map<String,String> errorResponse = new HashMap<String,String>();
        errorResponse.put("mesage",ex.getMessage());
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(value=AuthorNotFoundException.class)
    public ResponseEntity<Object> authorNotFoundExceptionHandler(AuthorNotFoundException ex){
        log.error("Could not find Author with:\nException:\n"+ex);
        Map<String,String> errorResponse = new HashMap<String,String>();
        errorResponse.put("message",ex.getMessage());
        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }
}
