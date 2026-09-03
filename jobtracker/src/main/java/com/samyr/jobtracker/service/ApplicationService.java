package com.samyr.jobtracker.service;

import com.samyr.jobtracker.exception.ApplicationNotFoundException;
import com.samyr.jobtracker.model.Application;

import com.samyr.jobtracker.model.Company;
import com.samyr.jobtracker.model.Status;
import com.samyr.jobtracker.repositories.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.List;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    CompanyService companyService;


    public Application createApplication(Application application){
        Application newApplication = new Application();
        newApplication.setCompany(validateCompany(application));
        newApplication.setRole(application.getRole());
        newApplication.setLink(application.getLink());
        newApplication.setApplication_date(application.getApplication_date());


        setStatus(newApplication, application);
        validateRole(newApplication);

        return applicationRepository.save(newApplication);

    }

    public Application getApplicationById(int id){
        return applicationRepository.findById(id)
                .orElseThrow(() -> new ApplicationNotFoundException(id));
    }

    public List<Application> applicationsList(){
        return applicationRepository.findAll();
    }

    public Application updateApplication(int id, Application application){
        Application existingApplication = getApplicationById(id);
        updateValidator(existingApplication, application);

        return applicationRepository.save(existingApplication);
    }

    public void deleteApplication(int id){
        Application application = getApplicationById(id);
        applicationRepository.delete(application);
    }

    private void validateRole(Application application){
        if(application.getRole()==null){
            throw new IllegalArgumentException("Role is required");
        }
    }

    private Company validateCompany(Application application){
        return companyService.validateCompany(application.getCompany());
    }


    private void setStatus(Application sourceApplication, Application targetApplication){
        if(targetApplication.getStatus()==null){
            sourceApplication.setStatus(Status.SAVED);
        }
        else{
            sourceApplication.setStatus(targetApplication.getStatus());
        }
    }

    private void updateStatus(Application sourceApplication, Application targetApplication){
        if(targetApplication.getStatus() != null && sourceApplication.getStatus() != targetApplication.getStatus()){
            sourceApplication.setStatus(targetApplication.getStatus());
        }
    }

    private void updateValidator(Application existingApplication, Application newApplication){
        if(newApplication.getCompany() != null){
            existingApplication.setCompany(validateCompany(newApplication));
        }

        if(newApplication.getRole() != null){
            existingApplication.setRole(newApplication.getRole());
            validateRole(existingApplication);
        }

        if(newApplication.getApplication_date() != null){
            existingApplication.setApplication_date(newApplication.getApplication_date());
        }

        if(newApplication.getStatus() != null){
            updateStatus(existingApplication, newApplication);
        }

        if(newApplication.getLink() != null){
            existingApplication.setLink(newApplication.getLink());
        }
    }


}
