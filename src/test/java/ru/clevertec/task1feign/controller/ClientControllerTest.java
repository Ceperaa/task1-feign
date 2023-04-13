package ru.clevertec.task1feign.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.clevertec.task1feign.model.Client;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static ru.clevertec.task1feign.util.EntitySupplier.*;

@ActiveProfiles("test")
@SpringBootTest
@ExtendWith(WireMockExtension.class)
@WireMockTest(httpPort = 8082)
@AutoConfigureMockMvc
class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private Client client;
    private ObjectMapper objectMapper;
    private String clientJson;
    private String clientInfoJson;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        objectMapper = new ObjectMapper();
        client = findClient();
        clientInfoJson = objectMapper.writeValueAsString(findCheckInfo());
        clientJson = objectMapper.writeValueAsString(client);
    }

    @Test
    void checkInfoNotExtended() throws Exception {
        client.setClientInfo(null);
        clientJson = objectMapper.writeValueAsString(client);
        stubFor(WireMock.post(urlPathEqualTo("/check"))
                .withRequestBody(equalToJson(clientInfoJson))
                .withHeader("authorization", equalTo(AUTHORIZATION))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("OK")
                        .withStatus(200)));

        mockMvc.perform(MockMvcRequestBuilders.post("/clients")
                        .content(clientJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("authorization", AUTHORIZATION))
                .andExpect(MockMvcResultMatchers.status().isNotExtended())
                .andReturn();
    }

    @Test
    void checkWorkerTestError() throws Exception {
        stubFor(WireMock.post(urlPathEqualTo("/check"))
                .withRequestBody(equalToJson(clientInfoJson))
                .withHeader("authorization", equalTo(AUTHORIZATION))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("error")
                        .withStatus(500)));

        mockMvc.perform(MockMvcRequestBuilders.post("/clients")
                        .content(clientJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("authorization", AUTHORIZATION))
                .andExpect(MockMvcResultMatchers.status().is5xxServerError())
                .andReturn();
    }

    @Test
    void checkWorkerTestOk() throws Exception {
        stubFor(WireMock.post(urlPathEqualTo("/check"))
                .withRequestBody(equalToJson(clientInfoJson))
                .withHeader("authorization", equalTo(AUTHORIZATION))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("OK")
                        .withStatus(200)));

        mockMvc.perform(MockMvcRequestBuilders.post("/clients")
                        .content(clientJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("authorization", AUTHORIZATION))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }
}