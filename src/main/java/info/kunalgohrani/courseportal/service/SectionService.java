package info.kunalgohrani.courseportal.service;

import info.kunalgohrani.courseportal.model.Section;

import java.util.List;

public interface SectionService {

    List<Section> getAllSectionsByCourseId(Long courseId);
    Section saveOrUpdateSectionInCourse(Long courseId,Section section);
    Long deleteSectionFromCourse(Long courseId,Long sectionId);
    Section getSectionByName(Long courseId,String name);
}
