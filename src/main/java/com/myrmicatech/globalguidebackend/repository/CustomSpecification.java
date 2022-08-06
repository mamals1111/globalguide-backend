package com.myrmicatech.globalguidebackend.repository;

import com.myrmicatech.globalguidebackend.entity.Account;
import com.myrmicatech.globalguidebackend.entity.Account_;
import com.myrmicatech.globalguidebackend.entity.Otp;
import com.myrmicatech.globalguidebackend.entity.Otp_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.*;

@Component
public class CustomSpecification {

   
    public static Specification<Account> accountHasEmail(String email) {
        return new Specification<Account>() {
            @Override
            public Predicate toPredicate(Root<Account> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get(Account_.EMAIL), email);
            }
        };
    }

    public static Specification<Account> accountHasOtp(String otp) {
        return new Specification<Account>() {
            @Override
            public Predicate toPredicate(Root<Account> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Join<Account, Otp> accountOtp = root.join(Account_.OTP);
                return criteriaBuilder.equal(accountOtp.get(Otp_.OTP), otp);
            }
        };
    }

   

    public static Specification<Otp> otpHasAccount(Account account) {
        return new Specification<Otp>() {
            @Override
            public Predicate toPredicate(Root<Otp> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get(Otp_.ACCOUNT), account);
            }
        };
    }

}
