package ru.clevertec.task1feign.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.clevertec.task1feign.exception.FieldNullException;
import ru.clevertec.task1feign.feign.ClientFeign;
import ru.clevertec.task1feign.model.*;
import ru.clevertec.task1feign.service.ClientService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientServiceImpl implements ClientService {

    private final ClientFeign clientFeign;
    private final static String FILTER = "N05";
    private final static String AUTHORIZATION = "1234567890";

    @Override
    public void checkWorker(Client client) {
        findCheckInfo(client)
                .stream()
                .filter((info) -> info.getCheckCode().equals(FILTER))
                .forEach((info) -> clientFeign.check(info, AUTHORIZATION));
    }

    private List<CheckInfo> findCheckInfo(Client client) {
        return Optional.of(client)
                .map(Client::getClientInfo)
                .map(ClientInfo::getClientDetails)
                .map(ClientDetail::getChecks)
                .map(Check::getChecks)
                .orElseThrow(() -> new FieldNullException(Client.class));
    }
}
