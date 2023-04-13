package ru.clevertec.task1feign.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.task1feign.model.Client;
import ru.clevertec.task1feign.service.ClientService;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    public void checkInfo(@RequestBody Client client) {
        clientService.checkWorker(client);
    }
}
