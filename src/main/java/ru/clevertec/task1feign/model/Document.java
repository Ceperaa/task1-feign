package ru.clevertec.task1feign.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Document {

    private String type;
    private Long number;
    private LocalDate date;
    private String series;
    private String termDate;

}
