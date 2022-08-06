package com.myrmicatech.globalguidebackend.entity.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewCountAllAndAvgRatingDto {

    private long totalReviews;
    private double avgRatings;
}
