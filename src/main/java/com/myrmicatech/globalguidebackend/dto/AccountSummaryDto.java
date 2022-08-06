package com.myrmicatech.globalguidebackend.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountSummaryDto {
    private double totalFollowers;
    private double totalFollowings;
    private double totalContributers;
}
