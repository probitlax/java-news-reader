package org.example.demo.demoproject.configuration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class RestTemplateConfiguration {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        //TODO: rootUri can be set here
        return restTemplateBuilder.
                setConnectTimeout(Duration.ofSeconds(2)).
                setReadTimeout(Duration.ofSeconds(2)).
                build();
    }





}
