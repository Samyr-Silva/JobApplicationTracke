package com.samyr.jobtracker.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Calendar;

@Entity
@Table(name = "application")
public class Application {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @JoinColumn(name = "company_id")
    @ManyToOne
    @NotNull
    private Company company;

    @Column(name = "role")
    @NotBlank
    private String role;

    @Column(name = "link")
    private String link;

    @Column(name = "application_date")
    @NotNull
    private LocalDate application_date;


    @Enumerated(EnumType.STRING)
    @NotBlank
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

    public LocalDate getApplication_date() {
        return application_date;
    }

    public void setApplication_date(LocalDate application_date) {
        this.application_date = application_date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
