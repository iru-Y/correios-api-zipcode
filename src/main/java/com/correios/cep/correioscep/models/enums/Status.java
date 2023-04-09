package com.correios.cep.correioscep.models.enums;

public enum Status {
    NEED_SETUP, // precisa baixar o csv dos correios
    SETUP_RUNNING,  // Está baixando o csv dos correios
    READY; // Serviço está apto para ser consumido
}
