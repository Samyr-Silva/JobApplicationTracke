package com.samyr.jobtracker.controller;

import com.samyr.jobtracker.model.Application;
import com.samyr.jobtracker.model.Status;
import com.samyr.jobtracker.service.ApplicationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/app")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;


    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResponseEntity<Application> createApplication(@Valid @RequestBody Application application){
        return new ResponseEntity<>(applicationService.createApplication(application), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get/{id}")
    public ResponseEntity<Application> getApplication(@PathVariable int id){
        return new ResponseEntity<>(applicationService.getApplicationById(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ResponseEntity<List<Application>> applicationList(){
        return new ResponseEntity<>(applicationService.applicationsList(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/applications/")
    public ResponseEntity<List<Application>> getApplicationsByStatus(@RequestParam Status status){
        return new ResponseEntity<>(applicationService.applicationsByStatus(status), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update/{id}")
    public ResponseEntity<Application> updateApplication(@PathVariable int id, @Valid @RequestBody Application application){
        return new ResponseEntity<>(applicationService.updateApplication(id, application), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
    public ResponseEntity<String> deleteApplication(@PathVariable int id){
        applicationService.deleteApplication(id);
        return new ResponseEntity<>("Application with the ID: " + id + " deleted", HttpStatus.OK);
    }

}
