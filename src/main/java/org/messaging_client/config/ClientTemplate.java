package org.messaging_client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ClientTemplate {
    @Bean
    public RestTemplate restTemplateBean() {
        return new RestTemplate();
    }
}
