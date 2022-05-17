package info.kunalgohrani.courseportal.service;

import info.kunalgohrani.courseportal.model.Course;

import java.util.List;

public interface CourseService {

    List<Course> getAllCourses();

    Course getCourseById(Long id);

    List<Course> getCourseByName(String name);

    Long saveCourse(Course course);

    Long updateCourse(Course course);

    Long deleteCourse(Long id);


}
