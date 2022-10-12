package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.model.Video;

import java.util.List;
@Repository
public interface VideoDao extends JpaRepository<Video, Long> {
    @Query("select v from Video v where v.lesson.id=:id")
    List<Video> getAllVideoById(Long id);
}
