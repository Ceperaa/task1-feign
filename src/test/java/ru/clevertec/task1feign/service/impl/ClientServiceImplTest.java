package ru.clevertec.task1feign.service.impl;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.clevertec.task1feign.util.EntitySupplier;
import ru.clevertec.task1feign.mapper.ClientInfoMapper;
import ru.clevertec.task1feign.model.*;
import ru.clevertec.task1feign.model.ClientInfoDto;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.clevertec.task1feign.util.EntitySupplier.AUTHORIZATION;

@ActiveProfiles("test")
@SpringBootTest
@ExtendWith(WireMockExtension.class)
@WireMockTest(httpPort = 8082)
@AutoConfigureMockMvc
class ClientServiceImplTest {

    @Autowired
    private ClientServiceImpl clientService;
    private Client client;

    @BeforeEach
    void setUp() {
        client = EntitySupplier.findClient();
    }

    @Test
    void toClientInfoDto() {
        ClientInfoMapper clientInfoMapper = Mappers.getMapper(ClientInfoMapper.class);

        ClientInfoDto clientInfoDto = clientInfoMapper.toDto(client);

        assertThat(clientInfoDto.isRestrictWithoutTIN()).isEqualTo(true);
        assertThat(clientInfoDto.isRestrictWithoutConflictExplained()).isEqualTo(true);
        assertThat(clientInfoDto.getOperatorLogin()).isEqualTo(AUTHORIZATION);
    }

    @Test
    void checkWorkerTestOk() {
        client = EntitySupplier.findClient();
        stubFor(WireMock.post(urlPathEqualTo("/check"))
                .withHeader("authorization", equalTo(AUTHORIZATION))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("OK")
                        .withStatus(200)));

        clientService.checkWorker(client);

        verify(exactly(1), postRequestedFor(urlEqualTo("/check")));
    }

}