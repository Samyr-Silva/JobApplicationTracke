package com.samyr.jobtracker.service;

import com.samyr.jobtracker.model.Application;

import com.samyr.jobtracker.model.Status;
import com.samyr.jobtracker.repositories.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Calendar;
import java.util.List;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;


    public Application createApplication(Application application){
        Application newApplication = new Application();
        newApplication.setCompany(application.getCompany());
        newApplication.setRole(application.getRole());
        newApplication.setLink(application.getLink());
        newApplication.setApplication_date(application.getApplication_date());
        setStatus(newApplication, application);
        validateRequiredField(newApplication.getRole(), "Role");
        validateDate(newApplication.getApplication_date());
        return applicationRepository.save(newApplication);

    }

    public Application getApplicationById(int id){
        return applicationRepository.findById(id)
                .orElseThrow();
    }

    public List<Application> applicationsList(){
        return applicationRepository.findAll();
    }

    public Application updateApplication(Application application){
        Application existingApplication = getApplicationById(application.getId());
        existingApplication.setCompany(application.getCompany());
        existingApplication.setRole(application.getRole());
        existingApplication.setLink(application.getLink());
        existingApplication.setApplication_date(application.getApplication_date());
        existingApplication.setStatus(application.getStatus());
        return applicationRepository.save(existingApplication);
    }

    public void deleteApplication(int id){
        Application application = getApplicationById(id);
        applicationRepository.delete(application);
    }

    private void validateRequiredField(String value, String message){
        if(value==null){
            throw new IllegalArgumentException(message + " is required");
        }
    }

    private void validateDate(Calendar calendar){
        calendar.
    }


    private void setStatus(Application application, Application newApplication){
        if(application.getStatus()==null){
            application.setStatus(Status.SAVED);
        }
        else application.setStatus(newApplication.getStatus());
    }

}
