package com.myrmicatech.globalguidebackend.payload;


import com.myrmicatech.globalguidebackend.enums.RoleType;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountWithTokenResponse {
    private String token;
    private UUID id;
    private String email;
    private String username;
    private String fullName;
    private RoleType role;
}


