package com.quiz.quizbuilder.security;

import com.quiz.quizbuilder.entity.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


public class CustomUserDetails implements UserDetails {

    public CustomUserDetails(User user, List<SimpleGrantedAuthority> authorities) {
        this.user = user;
        this.authorities = authorities;
    }

    @Getter
    private User user;

    @Getter
    private List<SimpleGrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return !user.isDeleted();
    }

    @Override
    public boolean isAccountNonLocked() {
        return !user.isDeleted();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !user.isDeleted();
    }

    @Override
    public boolean isEnabled() {
        return !user.isDeleted();
    }
}
