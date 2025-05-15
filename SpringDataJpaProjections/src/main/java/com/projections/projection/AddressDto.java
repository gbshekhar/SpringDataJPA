package com.projections.projection;

import java.util.Objects;

public class AddressDto {
    private final String zipCode;
    private final PersonDto person;

    public AddressDto(String zipCode, PersonDto person) {
        this.zipCode = zipCode;
        this.person = person;
    }

    public String getZipCode() {
        return zipCode;
    }

    public PersonDto getPerson() {
        return person;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AddressDto that = (AddressDto) o;
        return Objects.equals(zipCode, that.zipCode) && Objects.equals(person, that.person);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zipCode, person);
    }
}