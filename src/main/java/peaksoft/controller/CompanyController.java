package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import peaksoft.model.Company;
import peaksoft.service.impl.CompanyServiceImpl;

@Controller
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyServiceImpl companyService;

    @Autowired
    public CompanyController(CompanyServiceImpl companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public String allCompany(Model model) {
        model.addAttribute("companies", companyService.getAllCompany());
        return "/allCompany";
    }

    @GetMapping("/new")
    public String newCompany(Model model) {
        model.addAttribute("company", new Company());
        return "/newCompany";
    }

    @PostMapping("/saveCompany")
    public String saveCompany(@ModelAttribute("company") Company company) {
        companyService.addCompany(company);
        return "redirect:/companies";
    }

    @RequestMapping("/update/{id}")
    public ModelAndView updateCompany(@PathVariable("id") Long id) {
       ModelAndView view = new ModelAndView("/updateCompany");
       view.addObject("newCompany",companyService.getCompanyById(id));
       return view;
    }



    @PostMapping("/delete/{id}")
    public String deleteCompany(@PathVariable("id") Long id) {
        companyService.deleteCompany(id);
        return "redirect:/companies";
    }

}
