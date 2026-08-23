package com.samyr.jobtracker.repositories;

import com.samyr.jobtracker.model.Company;
import org.springframework.data.repository.Repository;

import java.util.List;


public interface CompanyRepository extends Repository<Company, Integer>{
    List<Company> findByLocation(String local);
}
