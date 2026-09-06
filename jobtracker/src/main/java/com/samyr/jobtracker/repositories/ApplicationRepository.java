package com.samyr.jobtracker.repositories;

import com.samyr.jobtracker.model.Application;
import com.samyr.jobtracker.model.Company;
import com.samyr.jobtracker.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;


public interface ApplicationRepository extends JpaRepository<Application, Integer> {
    public List<Application> findByStatus(Status status);

    public List<Application> findByCompany(String companyName);
}
