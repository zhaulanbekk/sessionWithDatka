package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Instructor;
import peaksoft.service.impl.CompanyServiceImpl;
import peaksoft.service.impl.InstructorServiceImpl;

@Controller
@RequestMapping("/instructors")
public class InstructorController {
    private final CompanyServiceImpl companyService;
    private final InstructorServiceImpl instructorService;

    @Autowired
    public InstructorController(CompanyServiceImpl companyService, InstructorServiceImpl instructorService) {
        this.companyService = companyService;

        this.instructorService = instructorService;
    }

    @GetMapping("/{id}")
    public String getAllInstructors(Model model, @PathVariable Long id) {
        model.addAttribute("instructors", instructorService.getAllInstructors(id));
        model.addAttribute("companyId", id);
        return "/instructor/allInstructors";
    }

    @GetMapping("/new/{id}")
    public String addInstructor(Model model, @PathVariable Long id) {
        model.addAttribute("instructor", new Instructor());
        model.addAttribute("companyId", id);
        return "instructor/newInstructor";
    }

    @PostMapping("/{id}/saveInst")
    public String saveInstructor(@ModelAttribute("instructor") Instructor instructor, @PathVariable("id") Long id) {
        Instructor instructor1 = new Instructor();
        instructor1.setSpecialization(instructor.getSpecialization());
        instructor1.setPhoneNumber(instructor.getPhoneNumber());
        instructor1.setFirstName(instructor.getFirstName());
        instructor1.setLastName(instructor.getLastName());
        instructor1.setEmail(instructor.getEmail());
        instructorService.addInstructor(id, instructor1);
        return "redirect:/instructors/" + id;
    }

    @GetMapping("/updateInst/{id}/{companyId}")
    public String updateInst(Model model,
                             @PathVariable Long id,
                             @PathVariable("companyId") Long companyId) {
        model.addAttribute("instructor", instructorService.getInstructorById(id));
        return "/instructor/update";
    }

    @PostMapping("/{id}/{companyId}/editInst")
    public String saveInst(@PathVariable("id") Long id,
                           @PathVariable("companyId") Long companyId,
                           @ModelAttribute Instructor instructor) {
        instructor.setCompany(companyService.getCompanyById(companyId));
        instructorService.updateInstructor(id, instructor);
        return "redirect:/instructors/{companyId}";
    }

    @PostMapping("{id}/{companyId}/delete")
    public String deleteInst(@PathVariable Long id,
                             @PathVariable("companyId") Long id2) {
        instructorService.deleteInstructor(id2);
        return "redirect:/instructors/{id}";
    }
}
