package com.myrmicatech.globalguidebackend.entity.dto;


import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewCountGroupByRatingDto {
    private int rating;
    private long total;
}
