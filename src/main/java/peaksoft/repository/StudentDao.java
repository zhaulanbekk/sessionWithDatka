package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import peaksoft.model.Student;

import java.util.List;

public interface StudentDao extends JpaRepository<Student, Long> {
    @Query("select s from Student s where s.company.id=:id")
    List<Student> getAllStudentById(Long id);
}
