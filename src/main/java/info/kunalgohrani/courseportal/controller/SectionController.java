package info.kunalgohrani.courseportal.controller;

import info.kunalgohrani.courseportal.model.Section;
import info.kunalgohrani.courseportal.service.SectionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/section")
public class SectionController {
    private final SectionService sectionService;


    public SectionController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @GetMapping("/all/{courseId}")
    public List<Section> getAllSectionsOfCourseAPI(@PathVariable String courseId) {
        List<Section> sections =
                sectionService.getAllSectionsByCourseId(Long.parseLong(courseId));
        return sections;
    }

    @GetMapping("/getbyname/{courseId}/{name}")
    public List<Section> getSectionByNameAndCourseId(@PathVariable String courseId, @PathVariable String name) {
        List<Section> sections =
                sectionService.getSectionByName(Long.parseLong(courseId), name);
        return sections;
    }

    @PostMapping("/save")
    public ResponseEntity<Object> saveOrUpdateSectionAPI(@RequestBody Section section) {
        Map<String, String> response = new HashMap<String, String>();
        Long id = sectionService.saveOrUpdateSectionInCourse(section);
        if (id != null) {
            response.put("success", String.valueOf(id));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("success", "-1");
            return new ResponseEntity<>(response,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteSectionAPI(@RequestBody Section section) {

        Map<String, String> response = new HashMap<String, String>();
        Long id = sectionService.deleteSectionFromCourse(section);
        if (id != null) {
            response.put("success", String.valueOf(id));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("success", "-1");
            return new ResponseEntity<>(response,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
