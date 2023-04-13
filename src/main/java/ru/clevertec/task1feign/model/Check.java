package ru.clevertec.task1feign.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Check {

    private boolean restrictWithoutConflictExplained;
    private boolean restrictWithoutTIN;
    private boolean restrictServicePackage;
    private boolean restrictAccountOpening;
    private String[] restrictAccountCurrencies;
    private List<CheckInfo> checks;
}
