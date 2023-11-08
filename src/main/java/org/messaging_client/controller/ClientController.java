package org.messaging_client.controller;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.messaging_client.service.ClientService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/client")
@Slf4j
public class ClientController {
    private final ClientService clientService;
    RestTemplate restTemplate = new RestTemplate();
    @Value("${message}")
    private String SERVER_URL;
    private Integer clientID;
    private Integer sessionID;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostConstruct
    public Integer registerClient() {
        clientID = restTemplate.postForObject(SERVER_URL + "register", null, Integer.class);
        log.info("Зарегистрировался под ID:" + clientID);
        return clientID;
    }

    @PostConstruct
    public Integer registerSession() {
        sessionID = restTemplate.postForObject(SERVER_URL + "startDialog/" + clientID, null, Integer.class);
        log.info("Сессия: " + sessionID);
        return sessionID;
    }


    @PostMapping("/update")
    public ResponseEntity<?> getUpdatesForMe() {
        return new ResponseEntity<>(clientService.getUpdatesForMe(clientID, sessionID, restTemplate), HttpStatus.OK);
    }

}
