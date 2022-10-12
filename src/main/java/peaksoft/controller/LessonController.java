package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Lesson;
import peaksoft.service.impl.CourseServiceImpl;
import peaksoft.service.impl.LessonServiceImpl;

@Controller
@RequestMapping("/lessons")
public class LessonController {

    private final LessonServiceImpl lessonService;
    private final CourseServiceImpl courseService;

    @Autowired
    public LessonController(LessonServiceImpl lessonService, CourseServiceImpl courseService) {
        this.lessonService = lessonService;
        this.courseService = courseService;
    }
    @GetMapping("/{id}")
    public String getAllLesson(Model model,@PathVariable Long id){
        model.addAttribute("lessons", lessonService.getAllLessons(id));
        model.addAttribute("courseId", id);
        return "/lesson/allLesson";
    }
    @GetMapping("/newLesson/{id}")
    public String addLesson(Model model, @PathVariable Long id){
        model.addAttribute("lesson", new Lesson());
        model.addAttribute("courseId",id);
        return "/lesson/newLesson";
    }
    @PostMapping("/{id}/saveLesson")
    public String saveLesson(@ModelAttribute("lesson") Lesson lesson, @PathVariable("id") Long id){
        Lesson lesson1 = new Lesson();
        lesson1.setLessonName(lesson.getLessonName());
        lessonService.addLesson(id,lesson1);
        return "redirect:/lessons/"+id;
    }

    @GetMapping("/updateLesson/{id}/{courseId}")
    public String updateLesson(Model model,
                                @PathVariable Long id,
                                @PathVariable("courseId") Long courseId) {
        model.addAttribute("lesson", lessonService.getLessonById(id));
        return "/lesson/updateLesson";
    }

    @RequestMapping("/{id}/{courseId}/editLesson")
    public String editLesson(@PathVariable("id") Long id,
                              @PathVariable("courseId") Long courseId,
                              @ModelAttribute ("lesson")Lesson lesson) {
      // lesson.setCourse(courseService.getCourseById(courseId));
        lessonService.updateLesson(id,lesson);
        return "redirect:/lessons/"+courseId;
    }

    @PostMapping("{id}/{courseId}/deleteLesson")
    public String deleteLesson(@PathVariable Long id,
                                @PathVariable("courseId") Long id2) {
        lessonService.deleteLesson(id);
        return "redirect:/lessons/"+id2;
    }
}
