package com.myrmicatech.globalguidebackend.service.account;

import com.myrmicatech.globalguidebackend.dto.AccountDto;
import com.myrmicatech.globalguidebackend.dto.AccountSummaryDto;
import com.myrmicatech.globalguidebackend.entity.Account;
import com.myrmicatech.globalguidebackend.entity.Otp;
import com.myrmicatech.globalguidebackend.enums.OtpStatus;
import com.myrmicatech.globalguidebackend.enums.RoleType;
import com.myrmicatech.globalguidebackend.mapper.MapStructMapper;
import com.myrmicatech.globalguidebackend.repository.AccountRepository;
import com.myrmicatech.globalguidebackend.repository.OtpRepository;
import com.myrmicatech.globalguidebackend.service.generic.GlobalGuideEntityServiceImpl;
import com.myrmicatech.globalguidebackend.util.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.myrmicatech.globalguidebackend.repository.CustomSpecification.accountHasEmail;
import static com.myrmicatech.globalguidebackend.repository.CustomSpecification.accountHasOtp;
import static org.springframework.data.jpa.domain.Specification.where;

@Service
public class AccountServiceImpl extends GlobalGuideEntityServiceImpl<UUID, Account> implements AccountService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private OtpRepository otpRepository;

    @Autowired
    private MapStructMapper mapStructMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AccountServiceImpl(AccountRepository accountRepository) {
        super(accountRepository);
    }


    @Override
    public Account save(AccountDto accountDto) throws Exception {
        Specification<Account> specification = where(accountHasEmail(accountDto.getEmail()));
        List<Account> accounts = accountRepository.findAll(specification);
        if (!accounts.isEmpty()) {
            throw new Exception("Account is already registered!");
        }

        Validate.isValidPassword(accountDto.getPassword());
        Validate.isValidUsername(accountDto.getUsername());
        Validate.isValidEmail(accountDto.getEmail());
        Validate.isMatchPassword(accountDto.getPassword(), accountDto.getConfirmPassword());
        Account account = mapStructMapper.accountDtoToAccount(accountDto);
        Otp otp = new Otp();
        account.setPasswordHash(passwordEncoder.encode(accountDto.getPassword()));
        if (accountDto.getEmail().equals("admin@gmail.com")) {
            account.setRole(RoleType.ADMIN);
        } else {
            account.setRole(RoleType.USER);
        }
        account.setOtp(otp);
//        Account savedAccount = accountRepository.saveAndFlush(account);
        otp.setAccount(account);
        otpRepository.saveAndFlush(otp);

//        Account accountWithOtp = findFirstByEmail(savedAccount.getEmail());

//        return accountWithOtp;
        return accountRepository.save(account);
    }

    @Override
    public Account findFirstByEmail(String email) throws Exception {
        Specification<Account> specification = where(accountHasEmail(email));
        List<Account> account = accountRepository.findAll(specification);
        if (account.isEmpty()) {
            throw new Exception("Cannot find account!");
        }
        return account.get(0);
    }

    @Override
    public Account findFirstById(UUID accountId) throws Exception {
        Optional<Account> optionalAccount = accountRepository.findById(accountId);
        if (optionalAccount.isEmpty()) {
            throw new Exception("Cannot find account by account id!");
        }

        return optionalAccount.get();
    }

    @Override
    public Account findFirstByEmailAndOtp(String email, String otp) throws Exception {
        Specification<Account> specification = accountHasOtp(otp);
        List<Account> accounts = accountRepository.findAll(specification);
        if (accounts.isEmpty()) {
            throw new Exception("Cannot find your email with the according otp");
        }

        return accounts.get(0);
    }

    @Override
    public Account updateWithOtp(Account account, String newPassword) throws Exception {
       
        Otp otp = account.getOtp();
        LocalDateTime expiryTime = otp.getExpiryTime();
        OtpStatus currentOtpStatus = otp.getOtpStatus();
        LocalDateTime now = LocalDateTime.now();
        if (expiryTime.isBefore(now) && currentOtpStatus == OtpStatus.NOT_USED) {
            otp.setOtpStatus(OtpStatus.USED);
            account.setPasswordHash(passwordEncoder.encode(newPassword));
            return accountRepository.save(account);
        } else {
            throw new Exception("Your OTP is timeout, please resend OTP");
        }
    }

    @Override
    public Account checkAccount() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return findFirstByEmail(email);
    }

    @Override
    public Account checkAdmin() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Account account = findFirstByEmail(email);
        if (account.getEmail().equals("admin@gmail.com")) {
            return account;
        } else {
            throw new Exception("Account is not ADMIN!");
        }
    }

    @Override
    public Account saveNewPassword(String email, String randomPassword) throws Exception {
        Account account = findFirstByEmail(email);
        account.setPasswordHash(passwordEncoder.encode(randomPassword));

        return accountRepository.save(account);
    }

    @Override
    public Account followAccount(UUID accountId) throws Exception {
        Account toFollowAccount = findFirstById(accountId);
        Account account = checkAccount();

        if (toFollowAccount.equals(account)) {
            throw new Exception("You cannot follow yourself!");
        }

        List<Account> followersToFollowAccount = toFollowAccount.getFollowers();
        List<Account> followingAccounts = account.getFollowings();

       
        boolean isFollowingAlready = followingAccounts.stream().anyMatch(fa -> fa.equals(toFollowAccount));
        if (isFollowingAlready) {
            throw new Exception("You have already followed this account!");
        }

       
        followersToFollowAccount.add(account);
        toFollowAccount.setFollowers(followersToFollowAccount);
        toFollowAccount.setTotalFollowers(toFollowAccount.getTotalFollowers() + 1);

       
        followingAccounts.add(toFollowAccount);
        account.setFollowings(followingAccounts);
        account.setTotalFollowings(account.getTotalFollowings() + 1);

        accountRepository.saveAndFlush(toFollowAccount);
        Account savedAccount = accountRepository.saveAndFlush(account);
        return savedAccount;
    }

    @Override
    public Account unfollowAccount(UUID accountId) throws Exception {
        Account toUnfollowAccount = findFirstById(accountId);
        Account account = checkAccount();

        if (toUnfollowAccount.equals(account)) {
            throw new Exception("You cannot unfollow yourself!");
        }

        List<Account> followersToUnfollowAccount = toUnfollowAccount.getFollowers();
        List<Account> followingAccounts = account.getFollowings();

       
        boolean isFollowingAlready = followingAccounts.stream().anyMatch(fa -> fa.equals(toUnfollowAccount));
        if (!isFollowingAlready) {
            throw new Exception("You have already unfollowed this account!");
        }

       
        followersToUnfollowAccount.remove(account);
        toUnfollowAccount.setFollowers(followersToUnfollowAccount);
        toUnfollowAccount.setTotalFollowers(toUnfollowAccount.getTotalFollowers() - 1);

       
        followingAccounts.remove(toUnfollowAccount);
        account.setFollowings(followingAccounts);
        account.setTotalFollowings(account.getTotalFollowings() - 1);

        accountRepository.saveAndFlush(toUnfollowAccount);
        Account savedAccount = accountRepository.saveAndFlush(account);
        return savedAccount;
    }

    @Override
    public boolean checkAccountFollowing(UUID accountId) throws Exception {
        Account followAccountToCheck = findFirstById(accountId);
        Account account = checkAccount();

        boolean isFollowingAccount = followAccountToCheck.getFollowers()
                .stream().anyMatch(fatc -> fatc.equals(account));

        return isFollowingAccount;
    }

    @Override
    public AccountSummaryDto summaryAccountById(UUID accountId) throws Exception {
        Account account = findFirstById(accountId);
        double totalFollowers = account.getTotalFollowers();
        double totalFollowings = account.getTotalFollowings();
        double totalContributers = account.getReviews().size();

        AccountSummaryDto accountSummaryDto = AccountSummaryDto.builder()
                .totalFollowers(totalFollowers)
                .totalFollowings(totalFollowings)
                .totalContributers(totalContributers)
                .build();
        return accountSummaryDto;
    }
}
