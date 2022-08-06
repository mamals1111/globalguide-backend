package com.myrmicatech.globalguidebackend.service.account;

import com.myrmicatech.globalguidebackend.dto.AccountDto;
import com.myrmicatech.globalguidebackend.dto.AccountSummaryDto;
import com.myrmicatech.globalguidebackend.dto.UserPrincial;
import com.myrmicatech.globalguidebackend.entity.Account;
import com.myrmicatech.globalguidebackend.repository.AccountRepository;
import com.myrmicatech.globalguidebackend.service.generic.GlobalGuideEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountService extends GlobalGuideEntityService<UUID, Account> {
    Account save(AccountDto accountDto) throws Exception;

    Account findFirstByEmail(String email) throws Exception;

    Account findFirstById(UUID accountId) throws Exception;

    Account findFirstByEmailAndOtp(String email, String otp) throws Exception;

    Account updateWithOtp(Account account, String newPassword) throws Exception;

    Account checkAccount() throws Exception;

    Account checkAdmin() throws Exception;

    Account saveNewPassword(String toEmail, String randomPassword) throws Exception;

    Account followAccount(UUID accountId) throws Exception;

    Account unfollowAccount(UUID accountId) throws Exception;

    boolean checkAccountFollowing(UUID accountId) throws Exception;

    AccountSummaryDto summaryAccountById(UUID accountId) throws Exception;

//    List<Account> findAllFollowAccount() throws Exception;

//    UserPrincial findByUsername(String username);
}
