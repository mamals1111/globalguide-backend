package com.myrmicatech.globalguidebackend.dto;

import com.myrmicatech.globalguidebackend.enums.ReactionType;
import lombok.*;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReactionDto {
    private ReactionType reactionType;
    private UUID reviewId;
}
