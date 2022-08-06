package com.myrmicatech.globalguidebackend.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LocationDto {
    private String name;
    private String description;
    private String address;
    private String website;
    private String phoneNumber;
    private List<UUID> categoryIds;
    private BigDecimal latitude;
    private BigDecimal longitude;
}
