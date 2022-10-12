package peaksoft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.model.Course;
import peaksoft.model.Lesson;
import peaksoft.repository.CourseDao;
import peaksoft.repository.LessonDao;

import java.util.List;

@Service
public class LessonServiceImpl  {

    private final LessonDao lessonDao;
    private final CourseDao courseDao;

    @Autowired
    public LessonServiceImpl(LessonDao lessonDao,CourseDao courseDao) {
        this.courseDao = courseDao;
        this.lessonDao = lessonDao;
    }


    public List<Lesson> getAllLessons(Long id) {
        return lessonDao.getAllLessonById(id);
    }


    public Lesson addLesson(Long id, Lesson lesson) {
        Course course = courseDao.getById(id);
        course.addLesson(lesson);
        lesson.setCourse(course);
        return lessonDao.save(lesson);
    }

    public Lesson getLessonById(Long id) {
        return lessonDao.findById(id).orElseThrow(() ->
                new NullPointerException("Instructor with " + id + " was not found"));
    }

    public Lesson updateLesson(Long id, Lesson lesson) {
        Lesson lesson1 = lessonDao.getById(id);
        lesson1.setLessonName(lesson.getLessonName());
        return lessonDao.save(lesson1);
    }

    public void deleteLesson(Long id) {
        lessonDao.deleteById(id);
    }
}
