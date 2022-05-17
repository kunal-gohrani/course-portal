package info.kunalgohrani.courseportal.repository;

import info.kunalgohrani.courseportal.model.Course;
import info.kunalgohrani.courseportal.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SectionRepository extends JpaRepository<Section,Long> {
    List<Section> findByCourseAndName(Course course, String name);
}
