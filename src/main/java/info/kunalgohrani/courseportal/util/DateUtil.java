package info.kunalgohrani.courseportal.util;


import info.kunalgohrani.courseportal.model.Course;
import info.kunalgohrani.courseportal.model.Section;

import java.time.LocalDate;

public class DateUtil {
    public static boolean checkDates(Course course, Section section) {
        LocalDate courseStartDate = course.getStartDate();
        LocalDate courseEndDate = course.getEndDate();
        LocalDate sectionStartDate = section.getStartDate();
        LocalDate sectionEndDate = section.getEndDate();
        if (courseStartDate != null && courseEndDate != null && sectionEndDate != null && sectionStartDate != null) {
            return (sectionStartDate.isAfter(courseStartDate) || sectionStartDate.isEqual(courseStartDate)) &&
                    (sectionEndDate.isBefore(courseEndDate) || sectionEndDate.isEqual(courseEndDate));
        }
        return false;
    }
    public static boolean checkDates(Course course) {
        LocalDate courseStartDate = course.getStartDate();
        LocalDate courseEndDate = course.getEndDate();
        if(courseEndDate!=null && courseStartDate!=null){
            return courseEndDate.isAfter(courseStartDate);
        }
        return false;
    }
}
