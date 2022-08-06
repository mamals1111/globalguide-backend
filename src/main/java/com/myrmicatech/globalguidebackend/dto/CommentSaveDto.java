package com.myrmicatech.globalguidebackend.dto;

import lombok.*;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentSaveDto {
    private String description;
    private UUID reviewId;
}
