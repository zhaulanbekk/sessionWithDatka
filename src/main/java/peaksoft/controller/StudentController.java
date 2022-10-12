package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Student;
import peaksoft.service.impl.CompanyServiceImpl;
import peaksoft.service.impl.CourseServiceImpl;
import peaksoft.service.impl.StudentServiceImpl;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final CompanyServiceImpl companyService;
    private final CourseServiceImpl courseService;
    private final StudentServiceImpl studentService;

    @Autowired
    public StudentController(CompanyServiceImpl companyService, CourseServiceImpl courseService, StudentServiceImpl studentService) {
        this.companyService = companyService;
        this.courseService = courseService;
        this.studentService = studentService;
    }
    @GetMapping("/{id}")
    public String getAllStudents(Model model, @PathVariable Long id){
        model.addAttribute("students", studentService.getAllStudents(id));
        model.addAttribute("companyId",id);
        return "/student/allStudent";
    }

    @GetMapping("/new/{id}")
    public String addStudent(Model model, @PathVariable Long id) {
        model.addAttribute("student", new Student());
        model.addAttribute("companyId", id);
        return "student/newStudent";
    }

    @PostMapping("/{id}/save")
    public String saveStudent(@PathVariable("id") Long id,@ModelAttribute("student")Student student) {
        Student student1 = new Student();
        student1.setFirstName(student.getFirstName());
        student1.setLastName(student.getLastName());
        student1.setEmail(student.getEmail());
        student1.setStudyFormat(student.getStudyFormat());
        studentService.addStudent(id, student1);
        return "redirect:/students/ " + id;
    }

    @GetMapping("/updateStudent/{id}/{companyId}")
    public String updateStudent(Model model,
                             @PathVariable Long id,
                             @PathVariable("companyId") Long companyId) {
        model.addAttribute("student", studentService.getStudentById(id));
        return "/student/updateStudent";
    }

    @PostMapping("/{id}/{companyId}/editStudent")
    public String saveStudent(@PathVariable("id") Long id,
                           @PathVariable("companyId") Long companyId,
                           @ModelAttribute Student student) {
        student.setCompany(companyService.getCompanyById(companyId));
        studentService.updateStudent(id,student);
        return "redirect:/students/{companyId}";
    }

    @PostMapping("{id}/{companyId}/deleteStudent")
    public String deleteStudent(@PathVariable Long id,
                             @PathVariable("companyId") Long id2) {
        studentService.deleteStudent(id2);
        return "redirect:/students/{id}";
    }
}
