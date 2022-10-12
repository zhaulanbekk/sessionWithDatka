package peaksoft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.model.Lesson;
import peaksoft.model.Video;
import peaksoft.repository.LessonDao;
import peaksoft.repository.VideoDao;

import java.util.List;

@Service
public class VideoServiceImpl  {

    private final VideoDao videoDao;
    private final LessonDao lessonDao;

    @Autowired
    public VideoServiceImpl(VideoDao videoDao,LessonDao lessonDao) {
        this.videoDao = videoDao;
        this.lessonDao = lessonDao;
    }

    public List<Video> getAllVideos(Long id) {
        return videoDao.getAllVideoById(id);
    }

    public Video addVideo(Long id, Video video) {
        Lesson lesson = lessonDao.getById(id);
        lesson.setVideo(video);
        video.setLesson(lesson);
        return videoDao.save(video);
    }

    public Video getVideoById(Long id) {
        return videoDao.getById(id);
    }

    public void updateVideo(Long id, Video video) {
        Video video1 = videoDao.getById(id);
        video1.setVideoName(video.getVideoName());
        video1.setLink(video.getLink());
        videoDao.save(video1);
    }

    public void deleteVideo(Long id) {
        videoDao.deleteById(id);
    }
}
