package com.myrmicatech.globalguidebackend.dto;

import lombok.*;

import java.time.LocalTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TimeDto {
    private String weekDay;
    private LocalTime openTime;
    private LocalTime closeTime;
    private String locationId;
}
