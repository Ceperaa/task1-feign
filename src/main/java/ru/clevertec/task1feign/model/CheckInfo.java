package ru.clevertec.task1feign.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CheckInfo {

    private String checkCode;
    private String description;
    private String checkResultCode;
    private boolean partial;
    private boolean incorrect;
    private Document document;
    private String[] clients;
    private int countryCode;
    private boolean riskFlags;
    private String[] attributes;

}
