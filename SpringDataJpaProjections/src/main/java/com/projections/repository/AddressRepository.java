package com.projections.repository;

import com.projections.entity.Address;
import com.projections.projection.AddressDto;
import com.projections.projection.AddressView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    //Nested JPA projection Using Interface and  Derived Query
    List<AddressView> getAddressByState(String state);

    //Nested JPA Projection Using Interface and Custom Query
    @Query("SELECT a.zipCode as zipCode, a.person as person  FROM Address a WHERE a.state= :state")
    List<AddressView>getAddressViewByState(@Param("state") String state);

    //Class based projection
    List<AddressDto> findAddressByState(String state);
}