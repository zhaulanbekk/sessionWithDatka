package peaksoft.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "companies")
@NoArgsConstructor
@Getter
@Setter
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_seq")
    @SequenceGenerator(name = "company_seq", sequenceName = "company_seq", allocationSize = 1)
    private Long id;
    @Column(name = "company_name")
    private String companyName;
    @Column(name = "located_country")
    private String locatedCountry;

    public Company(String companyName, String locatedCountry) {
        this.companyName = companyName;
        this.locatedCountry = locatedCountry;
    }

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "company",fetch = FetchType.LAZY)
    private List<Course> courseList;

    public void addCourse(Course course) {
        courseList.add(course);
    }
    public void addInst(Instructor instructor){
        instructorList.add(instructor);
    }

    public void addStudent(Student student){
        studentList.add(student);
    }

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "company")
    private List<Student> studentList;


    @OneToMany(cascade = CascadeType.ALL,mappedBy = "company")
    private List<Instructor>instructorList;


}
