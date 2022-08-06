package com.myrmicatech.globalguidebackend.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemDto {

    private String name;

    private Integer defaultLevel;

    private Integer defaultEnergy;

    private Integer defaultPoint;

}
