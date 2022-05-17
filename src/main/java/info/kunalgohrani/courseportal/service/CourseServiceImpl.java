package info.kunalgohrani.courseportal.service;

import info.kunalgohrani.courseportal.exception.CourseAlreadyExistsException;
import info.kunalgohrani.courseportal.exception.CourseNotPresentException;
import info.kunalgohrani.courseportal.exception.DatesException;
import info.kunalgohrani.courseportal.model.Course;
import info.kunalgohrani.courseportal.model.Section;
import info.kunalgohrani.courseportal.repository.CourseRepository;
import info.kunalgohrani.courseportal.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> getAllCourses() {
        log.info("----In CourseServiceImpl.getAllCourses----");
        List<Course> allCourses = courseRepository.findAll();
        log.info("----Out of CourseServiceImpl.getAllCourses----");
        return allCourses;
    }

    @Override
    public Course getCourseById(Long id) {
        log.info("----In CourseServiceImpl.getCourseById----");
        Course course = courseRepository.findById(id).orElse(null);
        if (course == null) {
            throw new CourseNotPresentException("Course doesn't exists!");
        }
        log.info("----Out of CourseServiceImpl.getCourseById----");
        return course;
    }

    @Override
    public List<Course> getCourseByName(String name) {
        log.info("----In CourseServiceImpl.getCourseByName----");
        List<Course> courses = courseRepository.findByName(name);
        log.info("----Out of CourseServiceImpl.getCourseByName----");

        return courses;
    }

    @Override
    @Transactional
    public Long saveCourse(Course course) {
        log.info("----In CourseServiceImpl.saveCourse----");


        Optional<Course> coursedb =
                courseRepository.findByNameAndAuthor(course.getName(),
                        course.getAuthor());

        /* Checking if course is already present in DB, if so then throws
        CourseAlreadyExistsException
         */
        if (coursedb.isPresent()) {
            log.info(String.valueOf(coursedb.get()));
            throw new CourseAlreadyExistsException(String.format("Course %s " +
                            "of Author %s %s already exists",
                    coursedb.get().getName(),
                    coursedb.get().getAuthor().getFirstName(),
                    coursedb.get().getAuthor().getLastName()));
        }

        // If course not present in DB then adding course

        if (DateUtil.checkDates(course)) {
            Course savedCourse;
            try {
                savedCourse = courseRepository.save(course);
                log.info("Course saved with ID=" + savedCourse.getId());
            } catch (Exception ex) {
                log.error("Error in saving course:\n" + ex);
                return null;
            }
            log.info("----Out of CourseServiceImpl.saveCourse----");
            return savedCourse.getId();
        } else {
            throw new DatesException("Please check course start and end dates");
        }

    }

    @Override
    @Transactional
    public Long updateCourse(Course course) {
        log.info("----In CourseServiceImpl.updateCourse----");
        Course courseDb = getCourseById(course.getId());
        Course updatedCourse;
        try {
            updatedCourse = courseRepository.save(course);
        } catch (Exception ex) {
            log.error("Exception while updating course in CourseServiceImpl" +
                    ".updateCourse:\n" + ex);
            return null;
        }
        return updatedCourse.getId();
    }

    @Override
    @Transactional
    public Long deleteCourse(Long id) {
        log.info("----In CourseServiceImpl.deleteCourse----");
        Course course = getCourseById(id);
        try {
            courseRepository.delete(course);
        } catch (Exception ex) {
            log.error("Exception while deleting course in CourseServiceImpl" +
                    ".deleteCourse:\n" + ex);
            return null;
        }
        log.info("----Out of CourseServiceImpl.deleteCourse----");
        return id;

    }


}
