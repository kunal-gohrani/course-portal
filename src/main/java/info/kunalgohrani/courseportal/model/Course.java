package info.kunalgohrani.courseportal.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"sections"})
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String name;

    @ManyToOne(optional=false)
    @JoinColumn(name="author_id",referencedColumnName = "id")
    @JsonIgnoreProperties("courses")
    private Author author;

    private LocalDate startDate;

    private LocalDate endDate;

    @Lob
    private String description;

    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL)
    @JsonIgnoreProperties("course")
    private List<Section> sections = new ArrayList<>();


}
