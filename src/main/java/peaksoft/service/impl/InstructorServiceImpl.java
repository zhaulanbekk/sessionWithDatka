package peaksoft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.model.Company;
import peaksoft.model.Instructor;
import peaksoft.repository.CompanyDao;
import peaksoft.repository.InstructorDao;

import java.util.List;

@Service
public class InstructorServiceImpl {

    private final InstructorDao instructorDao;
    private final CompanyDao companyDao;

    @Autowired
    public InstructorServiceImpl(InstructorDao instructorDao, CompanyDao companyDao) {
        this.instructorDao = instructorDao;
        this.companyDao = companyDao;
    }


    public List<Instructor> getAllInstructors(Long id) {
        return instructorDao.getAllById(id);
    }

    public Instructor addInstructor(Long id, Instructor instructor) {
        Company company = companyDao.findById(id).get();
        company.addInst(instructor);
        instructor.setCompany(company);
      return   instructorDao.save(instructor);
    }

    public Instructor getInstructorById(Long id) {
        return instructorDao.findById(id).orElseThrow(() ->
                new NullPointerException("Instructor with " + id + " was not found"));
    }

    public Instructor updateInstructor(Long id, Instructor instructor) {
        Instructor instructor1 = instructorDao.getById(id);
        instructor1.setFirstName(instructor.getFirstName());
        instructor1.setLastName(instructor.getLastName());
        instructor1.setPhoneNumber(instructor.getPhoneNumber());
        instructor1.setEmail(instructor.getEmail());
        instructor1.setSpecialization(instructor.getSpecialization());
        return instructorDao.save(instructor1);
    }

    public void deleteInstructor(Long id) {
        instructorDao.deleteById(id);
    }

//    public void assignInstructorToCourse(Long instructorId, Long courseId) {
//    instructorDao.assignInstructorToCourse(instructorId,courseId);
//    }
}
