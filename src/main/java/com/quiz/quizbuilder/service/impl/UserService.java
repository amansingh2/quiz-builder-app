package com.quiz.quizbuilder.service.impl;

import com.quiz.quizbuilder.dto.LoginRequest;
import com.quiz.quizbuilder.entity.User;
import com.quiz.quizbuilder.repository.UserRepository;
import com.quiz.quizbuilder.security.CustomAuthenticationProvider;
import com.quiz.quizbuilder.security.CustomUserDetails;
import com.quiz.quizbuilder.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RequiredArgsConstructor
@Service(value = "userService")
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomAuthenticationProvider authenticationProvider;

    @Override
    public User create(User user) {
        if (StringUtils.hasLength(user.getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        } else throw new ResponseStatusException(HttpStatus.OK, "Invalid request, password cannot be empty");
        if (!StringUtils.hasLength(user.getEmail())) {
            throw new ResponseStatusException(HttpStatus.OK, "Email cannot be empty");
        }

        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElse(new User());
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsernameIgnoreCase(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public String login(LoginRequest request) {
        return authenticationProvider.authenticateUser(request);
    }
    public User retrieveUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            return userDetails.getUser();
        }
        throw new RuntimeException();
    }
}

