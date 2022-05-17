package info.kunalgohrani.courseportal.service;

import info.kunalgohrani.courseportal.exception.CourseNotPresentException;
import info.kunalgohrani.courseportal.model.Course;
import info.kunalgohrani.courseportal.model.Section;
import info.kunalgohrani.courseportal.repository.SectionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class SectionServiceImpl implements SectionService {

    private final CourseService courseService;

    private final SectionRepository sectionRepository;

    public SectionServiceImpl(
            CourseService courseService,
            SectionRepository sectionRepository) {
        this.courseService = courseService;
        this.sectionRepository = sectionRepository;
    }


    @Override
    public List<Section> getAllSectionsByCourseId(Long courseId) {
        log.info("----In SectionServiceImpl.getAllSectionsByCourseId----");
        List<Section> sections;

        Course coursedb = courseService.getCourseById(courseId);

        if (!(coursedb == null)) {
            sections = coursedb.getSections();
        } else {
            return null;
        }
        log.info("----Out of SectionServiceImpl.getAllSectionsByCourseId----");

        return sections;
    }

    @Override
    public Long saveOrUpdateSectionInCourse(Section section) {
        log.info("----In SectionServiceImpl.saveOrUpdateSectionInCourse----");
        Course course =
                courseService.getCourseById(section.getCourse().getId());
        if (!(course == null)) {
            // Course Exists
            try {
                if (section.getId() != null && section.getCourse().getId() != null) {
                    // Section already present, update it
                    sectionRepository.save(section);

                } else {
                    // New Section
                    course.addSection(section);
                }
                courseService.updateCourse(course);
                log.info("----Out of SectionServiceImpl" +
                        ".saveOrUpdateSectionInCourse----");
                return 0L;

            } catch (Exception ex) {
                log.error("Error in saving or updating section for section " +
                        "id=" + section.getId());
                return null;
            }

        } else {
            throw new CourseNotPresentException("Could not save/update " +
                    "sections, course not found.");
        }
    }

    @Override
    public Long deleteSectionFromCourse(Section section) {
        log.info("----In SectionServiceImpl.deleteSectionFromCourse----");
        Course course =
                courseService.getCourseById(section.getCourse().getId());
        Optional<Section> sectionFound =
                sectionRepository.findByCourseAndId(course, section.getId());
        try {
            if (sectionFound.isPresent()) {
                sectionRepository.delete(sectionFound.get());
                Long sectionId = sectionFound.get().getId();
                log.info("----Out of SectionServiceImpl" +
                        ".deleteSectionFromCourse----");
                return sectionId;
            }
        } catch (Exception ex) {
            log.error(String.format("Error while deleting section with ID=%d " +
                    "from course ID=%d", section.getId(), course.getId()));
            return null;
        }
        return null;
    }

    @Override
    public List<Section> getSectionByName(Long courseId, String name) {
        Course course = courseService.getCourseById(courseId);
        List<Section> sections = sectionRepository.findByCourseAndName(course
                , name);
        return sections;
    }
}
