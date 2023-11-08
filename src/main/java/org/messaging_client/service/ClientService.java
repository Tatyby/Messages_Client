package org.messaging_client.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ClientService {
    @Value("${message}")
    private String SERVER_URL;


    public List<String> getUpdatesForMe(int clientID, int sessionID, RestTemplate restTemplate) {
        return restTemplate.getForObject(SERVER_URL + "getAllMessages/"+clientID+"/" + sessionID, ArrayList.class);
    }

}
