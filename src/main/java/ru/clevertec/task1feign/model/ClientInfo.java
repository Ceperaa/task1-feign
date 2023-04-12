package ru.clevertec.task1feign.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientInfo {

    private ClientDetail clientDetail;
    private String cus;
    private String sfa;
    private String callerEntityId;
    private String workflow;
    private String id;
    private String sessionId;
    private String sessionPhone;
}
