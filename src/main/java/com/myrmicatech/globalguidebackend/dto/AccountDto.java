package com.myrmicatech.globalguidebackend.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDto {
    private String fullName;
    private String email;
    private String username;
    private String password;
    private String confirmPassword;
}
