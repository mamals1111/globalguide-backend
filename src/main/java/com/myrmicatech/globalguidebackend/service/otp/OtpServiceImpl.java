package com.myrmicatech.globalguidebackend.service.otp;

import com.myrmicatech.globalguidebackend.entity.Account;
import com.myrmicatech.globalguidebackend.entity.Otp;
import com.myrmicatech.globalguidebackend.enums.OtpStatus;
import com.myrmicatech.globalguidebackend.repository.OtpRepository;
import com.myrmicatech.globalguidebackend.service.account.AccountService;
import com.myrmicatech.globalguidebackend.service.generic.GlobalGuideEntityServiceImpl;
import com.myrmicatech.globalguidebackend.util.CustomRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class OtpServiceImpl extends GlobalGuideEntityServiceImpl<UUID, Otp> implements OtpService {

    @Autowired
    private OtpRepository otpRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private CustomRandom customRandom;

    public OtpServiceImpl(OtpRepository otpRepository) {
        super(otpRepository);
    }

    @Override
    public String getOtp(String email) throws Exception {
        Account account = accountService.findFirstByEmail(email);
        if (account != null) {
            Otp otp = account.getOtp();
            boolean isOtpAvailable = checkOtpAvailable(account);
            if (!isOtpAvailable) {
                String randomOtp = customRandom.getRandomOTP();
                LocalDateTime newExpiryTime = LocalDateTime.now().plusMinutes(30);
                otp.setOtp(randomOtp);
                otp.setExpiryTime(newExpiryTime);
                otp.setOtpStatus(OtpStatus.NOT_USED);
                otpRepository.save(otp);
            }
            return otp.getOtp();
        } else {
            throw new Exception("Account is not found!");
        }
    }

    public boolean checkOtpAvailable(Account account) {
        Otp otp = account.getOtp();
        OtpStatus isUsed = otp.getOtpStatus();
        LocalDateTime expiryTime = otp.getExpiryTime();
        if (isUsed == OtpStatus.USED || expiryTime.isBefore(LocalDateTime.now())) {
            return false;
        } else {
            return true;
        }
    }
}
