package com.samyr.jobtracker.repositories;

import com.samyr.jobtracker.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ApplicationRepository extends JpaRepository<Application, Integer> {
}
