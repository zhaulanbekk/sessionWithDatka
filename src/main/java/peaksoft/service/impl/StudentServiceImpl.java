package peaksoft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.model.Company;
import peaksoft.model.Student;
import peaksoft.repository.CompanyDao;
import peaksoft.repository.StudentDao;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl  {

    private final StudentDao studentDao;
    private final CompanyDao companyDao;

    @Autowired
    public StudentServiceImpl(StudentDao studentDao,CompanyDao companyDao) {
        this.studentDao = studentDao;
        this.companyDao = companyDao;
    }

    public List<Student> getAllStudents(Long id) {
        return studentDao.getAllStudentById(id);
    }

    public void addStudent(Long id, Student student) {
        Company company = companyDao.getById(id);
        company.addStudent(student);
        student.setCompany(company);
        studentDao.save(student);
    }

    public Student getStudentById(Long id) {
        return studentDao.findById(id).orElseThrow(() ->
                new NullPointerException("Student with " + id + " was not found"));
    }


    public void updateStudent(Long id, Student student) {
        Student student1 = studentDao.getById(id);
        student1.setFirstName(student.getFirstName());
        student1.setLastName(student.getLastName());
        student1.setEmail(student.getEmail());
        student1.setStudyFormat(student.getStudyFormat());
        studentDao.save(student1);
    }

    public void deleteStudent(Long id) {
        studentDao.deleteById(id);
    }

    public void assignCourse(Long courseId, Student student) {

    }
}
