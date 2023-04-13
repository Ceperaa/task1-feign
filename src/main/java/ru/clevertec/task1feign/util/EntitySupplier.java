package ru.clevertec.task1feign.util;

import ru.clevertec.task1feign.model.*;

import java.util.List;

public class EntitySupplier {

    public static final String AUTHORIZATION = "1234567890";

    public static Check findCheck() {
        List<CheckInfo> builder = List.of(findCheckInfo());
        return Check.builder()
                .checks(builder)
                .restrictWithoutTIN(true)
                .restrictWithoutConflictExplained(true)
                .build();
    }

    public static CheckInfo findCheckInfo() {
        return CheckInfo.builder()
                .checkCode("N05")
                .build();
    }

    public static ClientDetail findCheckDetail() {
        return ClientDetail.builder().checks(findCheck()).build();
    }

    public static ClientInfo findClientInfo() {
        return ClientInfo.builder()
                .clientDetails(findCheckDetail())
                .build();
    }

    public static Client findClient() {
        return Client.builder()
                .clientInfo(findClientInfo())
                .operatorLogin(AUTHORIZATION)
                .build();
    }
}
