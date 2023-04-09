package com.correios.cep.correioscep.controllers;


import com.correios.cep.correioscep.exceptions.NoContentException;
import com.correios.cep.correioscep.exceptions.NotReadyException;
import com.correios.cep.correioscep.models.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.correios.cep.correioscep.services.CorreiosService;

@RestController
@RequestMapping(value = "/zipcode")
public class CorreiosController {
    @Autowired
    private CorreiosService correiosService;

    @GetMapping("/{zipcode}")
    public Address getAddressByZipcode(@PathVariable String zipcode) throws NoContentException, NotReadyException {
        return correiosService.findByZipcode(zipcode);
    }
}
