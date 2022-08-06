package com.myrmicatech.globalguidebackend.controller;

import com.google.common.collect.Lists;
import com.myrmicatech.globalguidebackend.dto.*;
import com.myrmicatech.globalguidebackend.entity.Account;
import com.myrmicatech.globalguidebackend.service.account.AccountService;
import com.myrmicatech.globalguidebackend.service.email.EmailService;
import com.myrmicatech.globalguidebackend.service.otp.OtpService;
import com.myrmicatech.globalguidebackend.util.CustomRandom;
import io.swagger.annotations.Api;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
//import it.ozimov.springboot.mail.model.Email;
//import it.ozimov.springboot.mail.model.defaultimpl.DefaultEmail;
//import it.ozimov.springboot.mail.service.EmailService;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import java.security.Principal;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/accounts")
@Api(tags = {"Account Management Resource (Account Management Api)"})
@SwaggerDefinition(tags = {
        @Tag(name = "Account Management Resource", description = "Manage accounts")
})
@CrossOrigin
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private OtpService otpService;

    @Autowired
    public EmailService emailService;

    @Autowired
    private CustomRandom customRandom;

    @RequestMapping(method = RequestMethod.POST, value = "register")
    public ResponseEntity<Object> register(@RequestBody AccountDto accountDto) {
        try {
            Account account = accountService.save(accountDto);
            return new ResponseEntity<>(account, HttpStatus.CREATED);
        } catch (Exception e) {
            if (e.getMessage().contains("Cannot find account!")) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

//    @RequestMapping(method = RequestMethod.POST, path="generate_otp")
//    public ResponseEntity<Object> generateOtp(@RequestBody GenerateOtpDto generateOtpDto) {
//        try {
//            String toEmail = generateOtpDto.getEmail();
//            String otp = otpService.getOtp(toEmail);
//            String fromEmail = "ilovemamals2005@gmail.com";
//
//            final Email email = DefaultEmail.builder()
//                    .from(new InternetAddress(fromEmail))
//                    .to(Lists.newArrayList(new InternetAddress(toEmail)))
//                    .subject("OTP for Global Guide")
//                    .body("OTP to reset email: " + otp)
//                    .encoding("UTF-8").build();
//            emailService.send(email);
//            return new ResponseEntity<>("Message sent successfully!", HttpStatus.OK);
//
//        } catch (Exception e) {
//            if (e.getMessage().contains("Cannot find account!")) {
//                return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
//            }
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
//        }
//    }

//    @RequestMapping(method = RequestMethod.POST, path="forget_password")
//    public ResponseEntity<Object> forgetPassword(@RequestBody ForgetPasswordDto forgetPasswordDto) {
//        try {
//            String email = forgetPasswordDto.getEmail();
//            String otp = forgetPasswordDto.getOtp();
//            Account account = accountService.findFirstByEmailAndOtp(email, otp);
//            String newPassword = forgetPasswordDto.getNewPassword();
//            Account savedAccount = accountService.updateWithOtp(account, newPassword);
//            return new ResponseEntity<>(savedAccount, HttpStatus.OK);
//        } catch (Exception e) {
//            if (e.getMessage().contains("Cannot find account!")) {
//                return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
//            }
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//    }

    @RequestMapping(method = RequestMethod.GET, path="forget_password/{email}")
    public ResponseEntity<Object> forgetPasswordByEmail(@PathVariable("email") String toEmail) {
        try {
            String randomPassword = customRandom.generateRandomPassword(10);
            accountService.saveNewPassword(toEmail, randomPassword);

            EmailDto emailDto = EmailDto.builder()
                    .recipient(toEmail)
                    .msgBody("Your new password is: " + randomPassword)
                    .subject("Reset password for Global Guide Application")
                    .build();

            emailService.sendSimpleMail(emailDto);

            return new ResponseEntity<>("Message sent successfully!", HttpStatus.OK);

        } catch (Exception e) {
            if (e.getMessage().contains("Cannot find account!")) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "private/findMe")
    public ResponseEntity<Object> findMe() {
        try {
            Account account = accountService.checkAccount();
            return new ResponseEntity<>(account, HttpStatus.OK);
        } catch (Exception e) {
            if (e.getMessage().contains("Cannot find account!")) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "findById/{accountId}")
    public ResponseEntity<Object> findById(@PathVariable("accountId") UUID accountId) {
        try {
            Account account = accountService.findFirstById(accountId);
            return new ResponseEntity<>(account, HttpStatus.OK);
        } catch (Exception e) {
            if (e.getMessage().contains("Cannot find account!")) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "private/followAccount/{followAccountId}")
    public ResponseEntity<Object> followAccount(@PathVariable("followAccountId") UUID followAccountId) {
        try {
            Account account = accountService.followAccount(followAccountId);
            return new ResponseEntity<>(account, HttpStatus.OK);
        } catch (Exception e) {
            if (e.getMessage().contains("Cannot find account!")) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "private/unfollowAccount/{unfollowAccountId}")
    public ResponseEntity<Object> unfollowAccount(@PathVariable("unfollowAccountId") UUID unfollowAccountId) {
        try {
            Account account = accountService.unfollowAccount(unfollowAccountId);
            return new ResponseEntity<>(account, HttpStatus.OK);
        } catch (Exception e) {
            if (e.getMessage().contains("Cannot find account!")) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "private/checkAccountFollowing/{followingAccountId}")
    public ResponseEntity<Object> checkAccountFollowing(@PathVariable("followingAccountId") UUID followingAccountId) {
        try {
            boolean isFollowingAccount = accountService.checkAccountFollowing(followingAccountId);
            return new ResponseEntity<>(isFollowingAccount, HttpStatus.OK);
        } catch (Exception e) {
            if (e.getMessage().contains("Cannot find account!")) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "findAllFollowings/{accountId}")
    public ResponseEntity<Object> findAllFollowings(@PathVariable("accountId") UUID accountId) {
        try {
            Account account = accountService.findFirstById(accountId);
            return new ResponseEntity<>(account.getFollowings(), HttpStatus.OK);
        } catch (Exception e) {
            if (e.getMessage().contains("Cannot find account!")) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "findAllFollowers/{accountId}")
    public ResponseEntity<Object> findAllFollowers(@PathVariable("accountId") UUID accountId) {
        try {
            Account account = accountService.findFirstById(accountId);
            return new ResponseEntity<>(account.getFollowers(), HttpStatus.OK);
        } catch (Exception e) {
            if (e.getMessage().contains("Cannot find account!")) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @RequestMapping(method = RequestMethod.GET, path = "summaryAccount/{accountId}")
    public ResponseEntity<Object> summaryAccountById(@PathVariable("accountId") UUID accountId) {
        try {
            AccountSummaryDto accountSummaryDto = accountService.summaryAccountById(accountId);
            return new ResponseEntity<>(accountSummaryDto, HttpStatus.OK);
        } catch (Exception e) {
            if (e.getMessage().contains("Cannot find account!")) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

//    stMapping("/login")
//    public ResponseEntity<?> login(@RequestBody AccountDto accountDto){
//        UserPrincipal userPrincipal = userService.findByUsername(user.getUsername());
//        if (null == user || !new BCryptPasswordEncoder().matches(user.getPassword(), userPrincipal.getPassword())) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("tài khoản hoặc mật khẩu không chính xác");
//        }
//        Token token = new Token();
//        token.setToken(jwtUtil.generateToken(userPrincipal));
//        token.setTokenExpDate(jwtUtil.generateExpirationDate());
//        token.setCreatedBy(userPrincipal.getUserId());
//        tokenService.createToken(token);
//        return ResponseEntity.ok(token.getToken());
//    }
}
