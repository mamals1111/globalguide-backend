package com.myrmicatech.globalguidebackend.payload;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomResponse<T> {
    private T data;
    private int pageNumber;
    private int pageSize;
    private double totalPages;
    private int totalRecords;
}

