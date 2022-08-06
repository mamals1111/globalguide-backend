package com.myrmicatech.globalguidebackend.service.otp;

import com.myrmicatech.globalguidebackend.entity.Account;
import com.myrmicatech.globalguidebackend.entity.Otp;
import com.myrmicatech.globalguidebackend.service.generic.GlobalGuideEntityService;

import java.util.UUID;

public interface OtpService extends GlobalGuideEntityService<UUID, Otp> {
    String getOtp(String email) throws Exception;
}
