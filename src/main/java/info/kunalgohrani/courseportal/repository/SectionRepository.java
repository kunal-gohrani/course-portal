package info.kunalgohrani.courseportal.repository;

import info.kunalgohrani.courseportal.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectionRepository extends JpaRepository<Section,Long> {
}
