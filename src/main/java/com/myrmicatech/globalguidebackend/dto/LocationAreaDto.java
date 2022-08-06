package com.myrmicatech.globalguidebackend.dto;

import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LocationAreaDto {
    private BigDecimal latitudeOne;
    private BigDecimal latitudeTwo;
    private BigDecimal longitudeOne;
    private BigDecimal longitudeTwo;
}
