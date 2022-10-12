package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Course;
import peaksoft.model.Instructor;
import peaksoft.service.impl.CompanyServiceImpl;
import peaksoft.service.impl.CourseServiceImpl;

@Controller
@RequestMapping("/courses")
public class CourseController {
    private final CourseServiceImpl courseService;
    private final CompanyServiceImpl companyService;


    @Autowired
    public CourseController(CourseServiceImpl courseService, CompanyServiceImpl companyService) {
        this.courseService = courseService;
        this.companyService = companyService;
    }

    @GetMapping("/{id}")
    public String allCourses(@PathVariable("id") Long id, Model model,
                             @ModelAttribute("inst") Instructor instructor) {
        model.addAttribute("courses", courseService.getAllCourses(id));
        model.addAttribute("companyId", id);
//        model.addAttribute("instructors", instructorService.getAllInstructors(id));
        return "course/allCourses";
    }

    @GetMapping("/add/{id}")
    public String addCourses(Model model, @PathVariable Long id) {
        model.addAttribute("course", new Course());
        model.addAttribute("companyId", id);
        return "course/newCourse";
    }

    @PostMapping("/{id}/saveCourse")
    public String saveCourses(@ModelAttribute("course") Course course,
                              @PathVariable("id") Long id) {
        Course course1 = new Course();
        course1.setCourseName(course.getCourseName());
        course1.setImage(course.getImage());
        course1.setDuration(course.getDuration());
        course1.setDescription(course.getDescription());
        course1.setCompany(companyService.getCompanyById(id));
        courseService.addCourse(id, course1);
        return "redirect:/courses/" + id;
    }

    @GetMapping("/updateCourse/{id}/{companyId}")
    public String updateCourse(Model model, @PathVariable Long id,
                               @PathVariable("companyId") Long companyId) {
        model.addAttribute("course", courseService.getCourseById(id));
        return "/course/updateCourse";
    }

    @PostMapping("/{id}/{companyId}/editCourse")
    public String saveUpdateCourse(@PathVariable("id") Long id,
                                   @PathVariable("companyId") Long companyId,
                                   @ModelAttribute Course course) {
        course.setCompany(companyService.getCompanyById(companyId));
        courseService.updateCourse(id, course);
        return "redirect:/courses/{companyId}";
    }

    @PostMapping("{id}/{courseId}/delete")
    public String deleteCourse(@PathVariable Long id,
                               @PathVariable("courseId") Long id2) {
        courseService.deleteCourse(id2);
        return "redirect:/courses/{id}";
    }
//
//    @PostMapping("/{companyId}/{courseId}/saveAssign")
//    private String saveAssign(@PathVariable("courseId") Long courseId,
//                              @ModelAttribute("inst") Instructor instructor,
//                              @PathVariable("companyId") Long compId) {
//        System.out.println(instructor);
//        instructorService.assignInstructorToCourse(instructor.getId(), courseId);
//        return "redirect:/courses/" + compId;
//    }
}
