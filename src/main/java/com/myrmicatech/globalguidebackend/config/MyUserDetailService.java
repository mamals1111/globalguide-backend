package com.myrmicatech.globalguidebackend.config;

import com.myrmicatech.globalguidebackend.entity.Account;
import com.myrmicatech.globalguidebackend.enums.RoleType;
import com.myrmicatech.globalguidebackend.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Account account = null;
        try {
            account = accountService.findFirstByEmail(email);
        } catch (Exception e) {
            throw new RuntimeException("Cannot find account");
        }
        Collection<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        if (account.getRole().equals(RoleType.USER)) {
            simpleGrantedAuthorities.add(new SimpleGrantedAuthority("USER"));
        } else if (account.getRole().equals(RoleType.ADMIN)) {
            simpleGrantedAuthorities.add(new SimpleGrantedAuthority("ADMIN"));
        }

        UserDetails userDetails = new User(email, account.getPasswordHash(), simpleGrantedAuthorities);

        return userDetails;
    }
}
