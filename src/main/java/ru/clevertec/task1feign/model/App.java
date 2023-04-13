package ru.clevertec.task1feign.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class App {

    private String cus;
    private String sfa;
    private String callerEntityId;
    private String workflow;
    private String id;
    private String sessionId;
    private String sessionPhone;
}
