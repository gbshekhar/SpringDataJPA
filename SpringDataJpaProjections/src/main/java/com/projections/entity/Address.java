package com.projections.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Address {
 
    @Id
    private Long id;
 
    @OneToOne
    private Person person;
 
    private String state;
 
    private String city;
 
    private String street;

    private String zipCode;

    // getters and setters
}