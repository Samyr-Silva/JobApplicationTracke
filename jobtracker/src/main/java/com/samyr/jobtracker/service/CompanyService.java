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

    public Company getCompanyById(int id){
        return companyRepository.findById(id).
                orElseThrow(); // change this after
    }

    public Company updateCompany(Company company){
        Company companyNew = getCompanyById(company.getId());
        companyNew.setName(company.getName());
        companyNew.setSite(company.getSite());
        companyNew.setLocalization(company.getLocalization());
        return companyRepository.save(companyNew);
    }

    public List<Company> companiesList(){
        return companyRepository.findAll();
    }

    public void deleteCompanyById(int id){
        Company company = getCompanyById(id);
        companyRepository.delete(company);
    }

    public Company validateCompany(Company company){
        if(company == null){
            throw new IllegalArgumentException("Company is Required");
        }
        return getCompanyById(company.getId());
    }

}
