package com.example.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SearchDTO {
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer countFrom;
    private Integer countTo;

    private Double priceFrom;
    private Double priceTo;
}
