package com.myrmicatech.globalguidebackend.dto;

import com.myrmicatech.globalguidebackend.entity.Account;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UploadFileDto {
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;
    private Account account;
    private UUID locationBannerId;
    private UUID locationAvatarId;
    private UUID reviewId;
}


