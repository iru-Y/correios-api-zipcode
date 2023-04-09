package com.correios.cep.correioscep.services;

import com.correios.cep.correioscep.CorreiosCepApplication;
import com.correios.cep.correioscep.exceptions.NoContentException;
import com.correios.cep.correioscep.exceptions.NotReadyException;
import com.correios.cep.correioscep.models.Address;
import com.correios.cep.correioscep.models.AddressStatus;
import com.correios.cep.correioscep.models.enums.Status;
import com.correios.cep.correioscep.repositories.AddressStatusRepository;
import com.correios.cep.correioscep.repositories.SetupRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import com.correios.cep.correioscep.repositories.AddressRepository;



@Service
public class CorreiosService {
    private final Logger logger = LoggerFactory.getLogger(CorreiosService.class);
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private AddressStatusRepository addressStatusRepository;
    @Autowired
    private SetupRepository setupRepository;
    public Status getStatus(){
        return this.addressStatusRepository.findById(AddressStatus.DEFAULT_ID)
                .orElse(AddressStatus.builder().status(Status.NEED_SETUP).build())
                .getStatus();
    }
    public Address findByZipcode(String zipcode) throws NoContentException, NotReadyException{
        if(!getStatus().equals(Status.READY)) throw new NotReadyException();

        return addressRepository.findById(zipcode)
                .orElseThrow(NoContentException::new);
    }
    private void saveStatus(Status status){
        this.addressStatusRepository.save(AddressStatus.builder()
                .id(AddressStatus.DEFAULT_ID)
                .status(status)
                .build());
    }
    @EventListener(ApplicationStartedEvent.class)
    protected void setupOnStartup(){
        try{
          this.setup();
        }catch (Exception e){
            CorreiosCepApplication.close(999);
        }
    }

    public void setup() throws Exception {
        logger.info("-----------");
        logger.info("-----------");
        logger.info("----------- SETUP RUNNING -----------");
        logger.info("-----------");
        logger.info("-----------");
        if(this.getStatus().equals(Status.NEED_SETUP)) {
            this.saveStatus(Status.SETUP_RUNNING);

            try {
                this.addressRepository.saveAll(
                        setupRepository.getFromOrigin());
            } catch (Exception e){
                this.saveStatus(Status.NEED_SETUP);
                throw e;
            }
            this.saveStatus(Status.READY);
        }
        logger.info("-----------");
        logger.info("-----------");
        logger.info("----------- SERVICE READY -----------");
    }
}
