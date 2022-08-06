package com.myrmicatech.globalguidebackend.config;

import org.mapstruct.Qualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Collections;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Autowired
    private ApplicationContext context;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        ApiAuthenticationFilter apiAuthenticationFilter = new ApiAuthenticationFilter(authenticationManager(authenticationConfiguration), context);
        apiAuthenticationFilter.setFilterProcessesUrl("/api/v1/accounts/login");


        httpSecurity.cors().configurationSource(request -> {
            CorsConfiguration cors = new CorsConfiguration();
            cors.setAllowedOrigins(Collections.singletonList("*"));
            cors.setAllowedMethods(Collections.singletonList("*"));
            cors.setAllowedHeaders(Collections.singletonList("*"));
            return cors;
        });

        httpSecurity.csrf().disable();
//        httpSecurity
//                .authorizeHttpRequests()
//                .anyRequest().authenticated()
//        httpSecurity.authorizeHttpRequests().antMatchers("/api/v1/accounts/login", "/api/v1/accounts/register","/v2/api-docs",
//                "/configuration/ui",
//                "/swagger-resources/**",
//                "/configuration/security",
//                "/swagger-ui.html",
//                "/webjars/**" ).permitAll();

        httpSecurity.authorizeHttpRequests().requestMatchers(request -> !(request.getRequestURI().contains("private")
                || request.getRequestURI().contains("admin"))).permitAll();

        httpSecurity
                .authorizeHttpRequests()
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .oauth2ResourceServer()
                .jwt();

        httpSecurity.addFilter(apiAuthenticationFilter);
        httpSecurity.addFilterBefore(new ApiAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() throws Exception {
//        return (web) -> {
//            web.ignoring().antMatchers("/api/v1/accounts/login", "/api/v1/accounts/register");
//            web.ignoring().antMatchers("/v2/api-docs",
//                    "/configuration/ui",
//                    "/swagger-resources/**",
//                    "/configuration/security",
//                    "/swagger-ui.html",
//                    "/webjars/**");
//        };
//    }
}
