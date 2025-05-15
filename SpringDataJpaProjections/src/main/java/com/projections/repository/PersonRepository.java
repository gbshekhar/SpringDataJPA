package com.projections.repository;

import com.projections.entity.Person;
import com.projections.projection.PersonDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    //Class based Projections
    PersonDto findByFirstName(String firstName);
}
