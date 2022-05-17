package info.kunalgohrani.courseportal.service;

import info.kunalgohrani.courseportal.model.Section;

import java.util.List;

public interface SectionService {

    List<Section> getAllSectionsByCourseId(Long courseId);

    Long saveOrUpdateSectionInCourse(Section section);

    Long deleteSectionFromCourse(Section section);

    List<Section> getSectionByName(Long courseId, String name);
}
