package peaksoft.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

import static javax.persistence.CascadeType.*;

@Entity
@Table(name = "courses")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_seq")
    @SequenceGenerator(name = "course_seq", sequenceName = "course_seq", allocationSize = 1)
    private Long id;
    @Column(name = "course_name")
    private String courseName;
    @Column(name = "date_of_start")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateOfStart;
    private int duration;
    private String image;
    private String description;

    public Course(String courseName, LocalDate dateOfStart, int duration, String image, String description) {
        this.courseName = courseName;
        this.dateOfStart = dateOfStart;
        this.duration = duration;
        this.image = image;
        this.description = description;
    }

    @ManyToOne(cascade = {MERGE, DETACH, REFRESH}, fetch = FetchType.LAZY)
    private Company company;

    @ManyToMany(cascade = {DETACH, MERGE, PERSIST, REFRESH}, mappedBy = "courseList")
    private List<Instructor> instructorList;

    public void addInst(Instructor instructor) {
        instructorList.add(instructor);
    }

    @OneToMany(cascade = ALL, mappedBy = "course")
    private List<Student> studentList;

    @OneToMany(cascade = ALL, mappedBy = "course")
    private List<Lesson> lessonList;


    public void addLesson(Lesson lesson) {
        lessonList.add(lesson);
    }
}
