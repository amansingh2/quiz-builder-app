package com.quiz.quizbuilder.security;

import com.quiz.quizbuilder.dto.LoginRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class CustomAuthenticationProvider extends DaoAuthenticationProvider {


    public CustomAuthenticationProvider(CustomUserDetailsService customUserDetailsService,
                                        PasswordEncoder passwordEncoder) {
        this.setUserDetailsService(customUserDetailsService);
        this.setPasswordEncoder(passwordEncoder);
    }

    public String authenticateUser(LoginRequest request) {
        String email = request.getEmail();
        String password = request.getPassword();

        Authentication authentication = this.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return (userDetails == null || userDetails.getUser() == null) ? "FAILED" : "SUCCESS";
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            return super.authenticate(authentication);
        } catch (AuthenticationException e) {
            throw new ResponseStatusException(HttpStatus.OK, "Invalid credentials");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
