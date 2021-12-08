package com.iba.electronicjournalservice.security.jwt;

import com.iba.electronicjournalservice.model.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public final class UserDetailsFactory {
    public UserDetailsFactory() {
    }

    public static UserDetailsImpl create(User user) {
        return new UserDetailsImpl(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhone(),
                user.getClassId(),
                user.getPassword(),
                true,
                mapToGrantedAuthorities(user.getRole())
        );
    }

    public static List<GrantedAuthority> mapToGrantedAuthorities(String authority) {
        List<GrantedAuthority> authorities= new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(authority));
        return authorities;
    }
}