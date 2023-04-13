package ru.clevertec.task1feign.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientInfoDto {

    private boolean restrictWithoutConflictExplained;
    private boolean restrictWithoutTIN;
    private String operatorLogin;
}
