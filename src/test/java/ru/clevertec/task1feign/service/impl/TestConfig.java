package ru.clevertec.task1feign.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestConfig {

    @Bean
    public WireMockServer wireMockServer() {
        return new WireMockServer(8002);
    }

    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }
}
