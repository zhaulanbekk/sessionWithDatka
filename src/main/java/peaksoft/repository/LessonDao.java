package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.model.Lesson;

import java.util.List;
@Repository
public interface LessonDao  extends JpaRepository<Lesson, Long> {
    @Query("select l from Lesson l where l.course.id=:id")
   List<Lesson> getAllLessonById(Long id);
}
