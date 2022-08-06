package com.myrmicatech.globalguidebackend.config;

import com.auth0.jwt.JWT;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.myrmicatech.globalguidebackend.dto.AccountDto;
import com.myrmicatech.globalguidebackend.entity.Account;
import com.myrmicatech.globalguidebackend.enums.RoleType;
import com.myrmicatech.globalguidebackend.payload.AccountWithTokenResponse;
import com.myrmicatech.globalguidebackend.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.stream.Collectors;

public class ApiAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    @Autowired
    private AccountService accountService;
//
//    public ApiAuthenticationFilter(AuthenticationManager authenticationManager, ApplicationContext ctx) {
//        this.authenticationManager = authenticationManager;
//        this.accountService = ctx.getBean(AccountService.class);
//    }

    public ApiAuthenticationFilter(AuthenticationManager authenticationManager, ApplicationContext ctx) {
        this.accountService = ctx.getBean(AccountService.class);
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try {
            String jsonData = request.getReader().lines().collect(Collectors.joining());
            Gson gson = new Gson();
           
            AccountDto accountDto = gson.fromJson(jsonData, AccountDto.class);

            UsernamePasswordAuthenticationToken userToken
                    = new UsernamePasswordAuthenticationToken(accountDto.getEmail(), accountDto.getPassword());

            return authenticationManager.authenticate(userToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

   
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User user = (User) authResult.getPrincipal();
        Calendar currentTime = Calendar.getInstance();
        currentTime.add(Calendar.DATE, 7);
        String accessToken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(currentTime.getTime())
                .withIssuer("t2004-e")
                .withClaim("role", user.getAuthorities().iterator().next().getAuthority())
                .sign(SecurityBean.algorithm());

//        HashMap<String, String> map = new HashMap<>();
//        map.put("accessToken", accessToken);
//        Account registeredAccount = accountService.findFirstByEmail(user.getUsername());
//        ResponseAccountWithToken responseAccountWithToken = ResponseAccountWithToken.builder()
//                .accessToken(accessToken)
//                .account(registeredAccount)
//                .build();
//        Gson gson = new GsonBuilder().serializeNulls().registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>) (json, type, jsonDeserializationContext) -> {
//
//            try{
//                return LocalDateTime.parse(json.getAsJsonPrimitive().getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//            } catch (DateTimeParseException e){
//                return LocalDateTime.parse(json.getAsJsonPrimitive().getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS"));
//            }
//
//        }).create();

//        String json = gson.toJson(responseAccountWithToken);
//        String json = new Gson().toJson(responseAccountWithToken);
        try {
            Account foundAccount = accountService.findFirstByEmail(user.getUsername());
            AccountWithTokenResponse accountWithTokenResponse = AccountWithTokenResponse.builder()
                    .token(accessToken)
                    .id(foundAccount.getId())
                    .fullName(foundAccount.getFullName())
                    .role(foundAccount.getRole())
                    .email(foundAccount.getEmail())
                    .username(foundAccount.getUsername())
                    .build();
            response.setContentType("application/json");

            Gson gson = new GsonBuilder().serializeNulls().registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>) (json, type, jsonDeserializationContext) -> {

                try {
                    return LocalDateTime.parse(json.getAsJsonPrimitive().getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                } catch (DateTimeParseException e) {
                    return LocalDateTime.parse(json.getAsJsonPrimitive().getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS"));
                }

            }).create();

            String json = gson.toJson(accountWithTokenResponse);

//            HashMap<String, String> map = new HashMap<>();
//            map.put("token", accessToken);
//            response.getWriter().println(new Gson().toJson(map));
            response.getWriter().println(json);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
//        response.getWriter().write(json);
    }
}
