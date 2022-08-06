package com.myrmicatech.globalguidebackend.dto;

import lombok.*;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewDto {
    private String description;
    private Integer rating;
    private UUID locationId;
    private UUID itemId;
}
