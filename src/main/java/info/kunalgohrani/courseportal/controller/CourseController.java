package info.kunalgohrani.courseportal.controller;

import info.kunalgohrani.courseportal.model.Course;
import info.kunalgohrani.courseportal.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/course/new")
    public ResponseEntity<Object> addCourseAPI(@RequestBody Course course) {
        Long savedCourseId = courseService.saveCourse(course);
        Map<String,String> response = new HashMap<String,String>();
        if (!(savedCourseId == null)) {
            response.put("id", String.valueOf(savedCourseId));
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            response.put("id", null);
            return new ResponseEntity<>(response,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/course/all")
    public List<Course> getAllCoursesAPI() {
        return courseService.getAllCourses();
    }

    @GetMapping("/course/{id}")
    public Course getCourseByIdAPI(@PathVariable String id) {
        return courseService.getCourseById(Long.valueOf(id));
    }

    @PutMapping("/course/update")
    public ResponseEntity<Object> updateCourseAPI(@RequestBody Course course) {
        Long updatedCourseId = courseService.updateCourse(course);
        Map<String,String> response = new HashMap<String,String>();
        if (!(updatedCourseId == null)) {
            response.put("message", "Updated");
            response.put("id", String.valueOf(updatedCourseId.toString()));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("message", "Error");
            response.put("id", null);
            return new ResponseEntity<>(response,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/course/delete/{id}")
    public ResponseEntity<Object> deleteCourseAPI(@PathVariable String id) {
        Map<String,String> response = new HashMap<String,String>();
        Long returnedId = courseService.deleteCourse(Long.parseLong(id));
        if (!(returnedId == null)) {
            response.put("message", "deleted");
            response.put("id",String.valueOf(returnedId));
        } else {
            response.put("message", "Failed to delete course");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
