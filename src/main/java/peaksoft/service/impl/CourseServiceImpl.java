package peaksoft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.model.Company;
import peaksoft.model.Course;
import peaksoft.repository.CompanyDao;
import peaksoft.repository.CourseDao;

import java.util.List;

@Service
public class CourseServiceImpl {

    private final CourseDao courseDao;
    private final CompanyDao companyDao;

    @Autowired
    public CourseServiceImpl(CourseDao courseDao,CompanyDao companyDao) {
        this.courseDao = courseDao;
        this.companyDao = companyDao;
    }


    public List<Course> getAllCourses(Long id) {
        return courseDao.getAllByCompanyId(id);
    }

    public void addCourse(Long id, Course course) {
//        Company company = courseDao.findById(id).get().getCompany();
//        company.addCourse(course);
//        course.setCompany(company);
//        courseDao.save(course);
        Company company = companyDao.findById(id).get();
        company.addCourse(course);
        course.setCompany(company);
        courseDao.save(course);
    }


    public Course getCourseById(Long id) {
        return courseDao.getById(id);
    }



    public void deleteCourse(Long id) {
        courseDao.deleteById(id);
    }

    public Course updateCourse(Long id, Course course){
        Course course1 = courseDao.getById(id);
        course1.setCourseName(course.getCourseName());
        course1.setImage(course.getImage());
        course1.setDuration(course.getDuration());
        course1.setDescription(course.getDescription());
        course1.setDateOfStart(course.getDateOfStart());
        return courseDao.save(course1);
    }

    public Course courseService(Course course) {
        Course newCourse = new Course();
        newCourse.setCourseName(course.getCourseName());
        newCourse.setDuration(course.getDuration());
        newCourse.setDateOfStart(course.getDateOfStart());
        newCourse.setDescription(course.getDescription());
        newCourse.setImage(course.getImage());
        return newCourse;
    }
}
