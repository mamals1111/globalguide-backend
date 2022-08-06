package com.myrmicatech.globalguidebackend.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailDto {

    private String recipient;
    private String msgBody;
    private String subject;
    private String attachment;
}
