package com.samyr.jobtracker.repositories;

import com.samyr.jobtracker.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Component;



@Component
public interface CompanyRepository extends JpaRepository<Company, Integer> {

}
