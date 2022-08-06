package com.myrmicatech.globalguidebackend.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ForgetPasswordDto {
    private String email;
    private String newPassword;
    private String otp;
}
