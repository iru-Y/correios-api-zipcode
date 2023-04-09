package com.correios.cep.correioscep.models;

import com.correios.cep.correioscep.models.enums.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class AddressStatus {
    public static final Integer DEFAULT_ID = 1;
    @Id
    private Integer id;
    private Status status;
}
