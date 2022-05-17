package info.kunalgohrani.courseportal.service;

import info.kunalgohrani.courseportal.exception.CourseNotPresentException;
import info.kunalgohrani.courseportal.model.Course;
import info.kunalgohrani.courseportal.model.Section;
import info.kunalgohrani.courseportal.repository.CourseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class SectionServiceImpl implements SectionService{

    private final CourseRepository courseRepository;

    public SectionServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }


    @Override
    public List<Section> getAllSectionsByCourseId(Long courseId) {
        log.info("----In getAllSectionsByCourseId----");
        List<Section> sections;

        Optional<Course> coursedb = courseRepository.findById(courseId);
        if(coursedb.isPresent()){
            sections = coursedb.get().getSections();
        }else{
            throw new CourseNotPresentException(String.format("Could not " +
                    "find course with ID=%d",courseId),courseId);
        }
        return sections;
    }

    @Override
    public Section saveOrUpdateSectionInCourse(Long courseId, Section section) {
        return null;
    }

    @Override
    public Long deleteSectionFromCourse(Long courseId, Long sectionId) {
        return null;
    }

    @Override
    public Section getSectionByName(Long courseId, String name) {
        return null;
    }
}
