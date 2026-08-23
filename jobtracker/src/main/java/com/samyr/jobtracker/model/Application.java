package com.samyr.jobtracker.model;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name = "application")
public class Application {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @JoinColumn(name = "company_id")
    @Autowired
    @ManyToOne
    private Company company;

    @Column(name = "role")
    private String role;

    @Column(name = "link")
    private String link;

    @Column(name = "application_date")
    private String application_date;

    @Column(name = "status")
    private String status;


}
