package com.projections.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Person {
 
    @Id
    private Long id;
 
    private String firstName;
 
    private String lastName;
 
    @OneToOne(mappedBy = "person")
    private Address address;

    // getters and setters
}