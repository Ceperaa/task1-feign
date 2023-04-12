package ru.clevertec.task1feign.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.clevertec.task1feign.model.CheckInfo;
import ru.clevertec.task1feign.model.ClientInfo;
import ru.clevertec.task1feign.service.ClientService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientServiceImpl implements ClientService {

    private final ClientFeign clientFeign;

    @Override
    public void checkWorker(ClientInfo checkInfo) {
        List<CheckInfo> checkInfo1 = checkInfo.getClientDetail().getCheck().getCheckInfo();
        List<ClientInfo> collect = checkInfo1.stream()
                .filter((v) -> v.getCheckCode() == "N05")
                .map((v) -> clientFeign.check())
                .peek((v)-> log.info(v.toString()))
                .collect(Collectors.toList());
    }
}
