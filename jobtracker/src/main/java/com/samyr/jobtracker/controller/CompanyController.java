package com.samyr.jobtracker.controller;

import com.samyr.jobtracker.model.Company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;


    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResponseEntity<Company> createCompany(@RequestBody Company company){
        return new ResponseEntity<>(companyService.createCompany(company), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get/{id}")
    public ResponseEntity<Company> getCompany(@PathVariable Integer id){
        return new ResponseEntity<>(companyService.getCompanyById(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ResponseEntity<List<Company>> listCompanies(){
        return new ResponseEntity<>(companyService.companiesList(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update/{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable int id, @RequestBody Company company){
        return new ResponseEntity<>(companyService.updateCompanyById(id, company), HttpStatus.ACCEPTED);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id){
        companyService.deleteCompanyById(id);
        return new ResponseEntity<>("Company with the ID: " + id + " deleted", HttpStatus.ACCEPTED);
    }

}
