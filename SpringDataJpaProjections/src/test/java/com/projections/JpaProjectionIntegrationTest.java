package com.projections;

import com.projections.projection.AddressDto;
import com.projections.projection.AddressView;
import com.projections.projection.PersonDto;
import com.projections.projection.PersonView;
import com.projections.repository.AddressRepository;
import com.projections.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;

@DataJpaTest
@Sql(scripts = "/projection-insert-data.sql")
@Sql(scripts = "/projection-clean-up-data.sql", executionPhase = AFTER_TEST_METHOD)
public class JpaProjectionIntegrationTest {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void whenUsingClosedProjections_thenViewWithRequiredPropertiesIsReturned() {
        //when
        AddressView addressView = addressRepository.getAddressByState("CA").get(0);
        PersonView personView = addressView.getPerson();

        //then
        assertThat(addressView.getZipCode()).isEqualTo("90001");
        assertThat(personView.getFirstName()).isEqualTo("John");
        assertThat(personView.getLastName()).isEqualTo("Doe");
    }

    @Test
    void whenUsingCustomQueryForNestedProjection_thenViewWithRequiredPropertiesIsReturned() {
        AddressView addressView = addressRepository.getAddressViewByState("CA").get(0);
        assertThat(addressView.getZipCode()).isEqualTo("90001");

        PersonView personView = addressView.getPerson();
        assertThat(personView.getFirstName()).isEqualTo("John");
        assertThat(personView.getLastName()).isEqualTo("Doe");
    }

    @Test
    public void whenUsingClassBasedProjections_thenDtoWithRequiredPropertiesIsReturned() {
        PersonDto personDto = personRepository.findByFirstName("John");

        assertThat(personDto.getFirstName()).isEqualTo("John");
        assertThat(personDto.getLastName()).isEqualTo("Doe");
    }

    @Test
    public void whenUsingClassedNestedProjections_thenViewWithRequiredPropertiesIsReturned() {
        //when
        AddressDto addressDto = addressRepository.findAddressByState("CA").get(0);
        PersonDto person = addressDto.getPerson();

        //then
        assertThat(addressDto.getZipCode()).isEqualTo("90001");
        assertThat(person.getFirstName()).isEqualTo("John");
        assertThat(person.getLastName()).isEqualTo("Doe");
    }
}