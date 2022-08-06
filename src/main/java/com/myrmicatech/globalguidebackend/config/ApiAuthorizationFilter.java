package com.myrmicatech.globalguidebackend.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ApiAuthorizationFilter extends OncePerRequestFilter {

    static Logger LOGGER = Logger.getLogger(ApiAuthorizationFilter.class.getSimpleName());

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        LOGGER.log(Level.WARNING, "doing filter");
        LOGGER.log(Level.WARNING, request.getServletPath());
        String servletPath = request.getServletPath();
//        if (servletPath.equals("/api/v1/accounts/login")
//                || servletPath.equals("/api/v1/accounts/register")
//                || servletPath.equals("/v2/api-docs")
//                || servletPath.equals("/configuration/ui")
//                || servletPath.contains("swagger-resources")
//                || servletPath.equals("/swagger-ui.html")
//                || servletPath.contains("webjars")
//                || servletPath.contains("csrf")
//        ) {
//            filterChain.doFilter(request, response);
//            return;
//        }
        if (!(servletPath.contains("private") || servletPath.contains("admin"))) {
            filterChain.doFilter(request, response);
            return;
        }

        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader == null) {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                    = new UsernamePasswordAuthenticationToken(null, null, null);
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            filterChain.doFilter(request, response);
            return;
        }
        authorizationHeader = authorizationHeader.replace("Bearer", "").trim();
        JWTVerifier jwtVerifier = JWT.require(SecurityBean.algorithm()).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(authorizationHeader);
        String username = decodedJWT.getSubject();
        String role = decodedJWT.getClaim("role").asString();
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                = new UsernamePasswordAuthenticationToken(username, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        LOGGER.log(Level.WARNING, role);
        LOGGER.log(Level.WARNING, username);
        filterChain.doFilter(request, response);
    }
}
