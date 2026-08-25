package com.samyr.jobtracker.controller;

import com.samyr.jobtracker.model.Company;
import com.samyr.jobtracker.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(value = "/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;


    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResponseEntity<Company> createCompany(@RequestBody Company company){

        return new ResponseEntity<>(companyService.createCompany(company), HttpStatus.CREATED);
    }

}
