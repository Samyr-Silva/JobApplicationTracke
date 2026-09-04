package com.samyr.jobtracker.repositories;

import com.samyr.jobtracker.model.Application;
import com.samyr.jobtracker.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ApplicationRepository extends JpaRepository<Application, Integer> {
    public List<Application> findByStatus(Status status);
}
