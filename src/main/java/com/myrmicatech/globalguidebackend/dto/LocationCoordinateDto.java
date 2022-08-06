package com.myrmicatech.globalguidebackend.dto;

import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LocationCoordinateDto {
    private BigDecimal latitude;
    private BigDecimal longitude;
}
