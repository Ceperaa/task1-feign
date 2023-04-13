package ru.clevertec.task1feign.service;

import ru.clevertec.task1feign.model.Client;

public interface ClientService {
    void checkWorker(Client client);
}
