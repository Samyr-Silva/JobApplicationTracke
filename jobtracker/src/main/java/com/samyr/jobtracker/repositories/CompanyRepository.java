package com.samyr.jobtracker.repositories;

import com.samyr.jobtracker.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CompanyRepository extends JpaRepository<Company, Integer> {
    List<Company> findByLocation(String local);
}
