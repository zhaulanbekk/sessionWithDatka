package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.model.Course;
import peaksoft.model.Instructor;

import java.util.List;
@Repository
public interface InstructorDao extends JpaRepository<Instructor, Long> {

    @Query("select i from Instructor i where i.company.id=:id")
    List<Instructor> getAllById(Long id);

}
