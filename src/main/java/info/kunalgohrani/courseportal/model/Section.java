package info.kunalgohrani.courseportal.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"course"})
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="course_id",referencedColumnName = "id")
    @JsonIgnoreProperties("sections")
    private Course course;

    @Lob
    private String name;

    private LocalDate startDate;

    private LocalDate endDate;

    @Lob
    private String description;
}
