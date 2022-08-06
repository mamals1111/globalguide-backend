package com.myrmicatech.globalguidebackend.dto;

import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LocationRadiusDto {
    private BigDecimal latitude;
    private BigDecimal longitude;
    private BigDecimal radius;
}
