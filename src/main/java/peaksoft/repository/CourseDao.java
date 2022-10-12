package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.model.Course;

import java.util.List;
@Repository
public interface CourseDao extends JpaRepository<Course, Long> {
    @Query("select c from Course c where c.company.id=:id")
    List<Course> getAllByCompanyId(Long id);

}
