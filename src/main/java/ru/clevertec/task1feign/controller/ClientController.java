package ru.clevertec.task1feign.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.task1feign.model.ClientInfo;
import ru.clevertec.task1feign.service.ClientService;

@RestController
@RequestMapping("/checks/")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    public void checkInfo(ClientInfo checkInfo) {
        clientService.checkWorker(checkInfo);
    }

}
