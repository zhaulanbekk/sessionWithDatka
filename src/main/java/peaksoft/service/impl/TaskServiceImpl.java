package peaksoft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.model.Lesson;
import peaksoft.model.Task;
import peaksoft.repository.LessonDao;
import peaksoft.repository.TaskDao;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TaskServiceImpl  {

    private final TaskDao taskDao;
    private final LessonDao lessonDao;

    @Autowired
    public TaskServiceImpl(TaskDao taskDao,LessonDao lessonDao) {
        this.taskDao = taskDao;
        this.lessonDao = lessonDao;
    }


    public List<Task> getAllTasks(Long id) {
        return taskDao.getAllTaskById(id);
    }

    public void updateTask(Long id, Task task) {
        Task task1 = taskDao.getById(id);
        task1.setTaskName(task.getTaskName());
        task1.setTaskText(task.getTaskText());
        taskDao.save(task1);
    }

    public Task getTaskById(Long id) {
        return taskDao.getById(id);
    }

    public Task addTask(Long lessonId, Task task) {
        Lesson lesson = lessonDao.getById(lessonId);
        lesson.addTask(task);
        task.setLesson(lesson);
        return taskDao.save(task);
    }

    public void deleteTask(Long id) {
        taskDao.deleteById(id);
    }
}
