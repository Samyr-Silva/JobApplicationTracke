package com.samyr.jobtracker.service;

import com.samyr.jobtracker.model.Company;
import com.samyr.jobtracker.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public Company createCompany(Company company){
        Company storeCompany = new Company();
        storeCompany.setName(company.getName());
        storeCompany.setSite(company.getSite());
        storeCompany.setLocalization(company.getLocalization());
        return companyRepository.save(storeCompany);
    }

    public Company getCompanyById(Integer id){
        return companyRepository.findById(id).
                orElseThrow(); // change this after
    }

    public Company updateCompanyById(int id, Company company){
        Company companyNew = getCompanyById(id);
        updateCompany(companyNew, company);
        return companyRepository.save(companyNew);
    }

    public List<Company> companiesList(){
        return companyRepository.findAll();
    }

    public void deleteCompanyById(Integer id){
        Company company = getCompanyById(id);
        companyRepository.delete(company);
    }

    public Company validateCompany(Company company){
        if(company == null){
            throw new IllegalArgumentException("Company is Required");
        }
        return getCompanyById(company.getId());
    }

    private void updateCompany(Company existingCompany, Company newCompany){
        if (newCompany.getLocalization() != null){
            existingCompany.setLocalization(newCompany.getLocalization());
        }
        if(newCompany.getName() != null){
            existingCompany.setName(newCompany.getName());
        }
        if (newCompany.getSite() != null){
            existingCompany.setSite(newCompany.getSite());
        }
    }

}
