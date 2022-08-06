package com.myrmicatech.globalguidebackend.util;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {

   
   
    public static final String USERNAME_REGEX = "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$";

   
    public static final String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";

   
    public static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";

    public static boolean isValidUsername(String username) throws Exception {
        Pattern pattern = Pattern.compile(USERNAME_REGEX);
        if (username == null) {
            throw new Exception("Username cannot be empty!");
        }
        Matcher matcher = pattern.matcher(username);
        if (!matcher.matches()) {
            throw new Exception("Username is not valid!");
        }
        return matcher.matches();
    }

    public static boolean isValidEmail(String email) throws Exception {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        if (email == null) {
            throw new Exception("Email cannot be empty!");
        }
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new Exception("Email is not valid!");
        }
        return matcher.matches();
    }

    public static void isValidPassword(String password) throws Exception {
        Pattern pattern = Pattern.compile(PASSWORD_REGEX);
        if (password == null) {
            throw new Exception("Password cannot be empty!");
        }
        Matcher matcher = pattern.matcher(password);
        if (!matcher.matches()) {
            throw new Exception("Password is not valid!");
        }
    }

    public static void isMatchPassword(String password, String confirmPassword) throws Exception {
        boolean isMatch = password.equals(confirmPassword);
        if (!isMatch) {
            throw new Exception("Password doesn't match!");
        }
    }

    public static void isBigDecimalPositive(BigDecimal bigDecimal) throws Exception {
        if (bigDecimal.compareTo(BigDecimal.valueOf(0)) <= 0) {
            throw new Exception("Number is not positive!");
        }
    }
}
