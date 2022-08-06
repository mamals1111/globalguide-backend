package com.myrmicatech.globalguidebackend.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchAndPaginationDto {
    private String keyword;
    private int pageNumber;
    private int pageSize;
}
