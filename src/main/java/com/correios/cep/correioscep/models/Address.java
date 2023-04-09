package com.correios.cep.correioscep.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serializable;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Address implements Serializable {
    @Id
    private String zipcode;
    private String street;
    private String district;
    private String city;
    private String state;
}
