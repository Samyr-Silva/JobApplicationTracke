package com.samyr.jobtracker.model;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;

@Entity
@Table(name = "application")
public class Application {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @JoinColumn(name = "company_id")
    @ManyToOne
    private Company company;

    @Column(name = "role")
    private String role;

    @Column(name = "link")
    private String link;

    @Column(name = "application_date")
    private String application_date;


    @Enumerated(EnumType.STRING)
    private Status status;

    public Integer getId(){
        return id;
    }

    public Company getCompany(){
        return company;
    }

    public void setCompany(Company company){
       this.company = company;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getApplication_date() {
        return application_date;
    }

    public void setApplication_date(String application_date) {
        this.application_date = application_date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
