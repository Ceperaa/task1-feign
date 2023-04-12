package ru.clevertec.task1feign.service;

import ru.clevertec.task1feign.model.CheckInfo;
import ru.clevertec.task1feign.model.ClientInfo;

public interface ClientService {
    void checkWorker(ClientInfo checkInfo);
}
