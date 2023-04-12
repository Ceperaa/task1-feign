package ru.clevertec.task1feign.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import ru.clevertec.task1feign.model.Check;
import ru.clevertec.task1feign.model.CheckInfo;
import ru.clevertec.task1feign.model.ClientDetail;
import ru.clevertec.task1feign.model.ClientInfo;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@SpringBootTest(classes = {TestConfig.class})
@AutoConfigureMockMvc
class ClientServiceImplTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClientServiceImpl clientService;

    @Autowired
    private WireMockServer wireMockServer;

    @Autowired
    protected ObjectMapper objectMapper;

    private ClientInfo build2;


    @BeforeEach
    void setUp() {
        List<CheckInfo> builder = List.of(CheckInfo.builder().checkCode("N05").build());
        Check build = Check.builder().checkInfo(builder).build();
        ClientDetail build1 = ClientDetail.builder().check(build).build();
        build2 = ClientInfo.builder().clientDetail(build1).build();
    }

    @Test
    void checkWorkerTest() throws Exception {
        String jsonCheckInfo = objectMapper.writeValueAsString(build2);

        wireMockServer.stubFor(WireMock.post(urlEqualTo("/check"))
                .withRequestBody(equalToJson(jsonCheckInfo))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(jsonCheckInfo)
                        .withStatus(200)));

        clientService.checkWorker(build2);
//        mockMvc.perform(post("checks/check"))
//                .andExpect(status().isOk());
    }
}