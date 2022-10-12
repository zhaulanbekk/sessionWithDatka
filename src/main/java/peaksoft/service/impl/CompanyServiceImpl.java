package peaksoft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.model.Company;
import peaksoft.repository.CompanyDao;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CompanyServiceImpl {

    private final CompanyDao companyDao;

    @Autowired
    public CompanyServiceImpl(CompanyDao companyDao) {
        this.companyDao = companyDao;
    }

    public List<Company> getAllCompany() {
        return companyDao.findAll();
    }

    public void addCompany(Company company) {
        companyDao.save(company);
    }


    public Company getCompanyById(Long id) {
        return companyDao.findById(id).get();
    }


    public void deleteCompany(Long id) {
        companyDao.deleteById(id);
    }
}
