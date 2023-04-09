package com.correios.cep.correioscep.repositories;

import com.correios.cep.correioscep.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, String> {

}
