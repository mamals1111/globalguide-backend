package com.myrmicatech.globalguidebackend.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LocationLogDto {

    private String name;
    private String description;
    private String address;
    private String website;
    private String phoneNumber;
    private String locationId;
}
