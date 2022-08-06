package com.myrmicatech.globalguidebackend.service.email;

import com.myrmicatech.globalguidebackend.dto.EmailDto;
import org.springframework.stereotype.Service;

public interface EmailService {

   
    String sendSimpleMail(EmailDto emailDto);

   
    String sendMailWithAttachment(EmailDto emailDto);
}
