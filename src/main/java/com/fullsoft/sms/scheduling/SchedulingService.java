package com.fullsoft.sms.scheduling;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SchedulingService {

    @Scheduled(cron = "0 * * * * *")
    public void mostrarMensaje(){
        log.info("Mostrando mensaje para mantener prendida la máquina (esto es una prueba)");
    }

}