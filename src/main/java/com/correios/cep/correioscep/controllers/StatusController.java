package com.correios.cep.correioscep.controllers;

import com.correios.cep.correioscep.exceptions.NotReadyException;
import com.correios.cep.correioscep.services.CorreiosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/status")
public class StatusController {
    @Autowired
    private CorreiosService correiosService;
    @GetMapping
    public String getStatus() throws NotReadyException {
        return "Service status " + correiosService.getStatus();
    }
}
