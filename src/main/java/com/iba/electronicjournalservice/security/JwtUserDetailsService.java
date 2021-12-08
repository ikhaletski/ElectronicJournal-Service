package com.iba.electronicjournalservice.security;

import com.iba.electronicjournalservice.logic.service.UserService;
import com.iba.electronicjournalservice.model.user.User;
import com.iba.electronicjournalservice.security.jwt.JwtUser;
import com.iba.electronicjournalservice.security.jwt.JwtUserFactory;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;



    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userService.findUserByEmail(email);
        if(user.isEmpty()) {
            throw new UsernameNotFoundException("User with email: " + email + " not found");
        }

        JwtUser jwtUser = JwtUserFactory.create(user.get());

        return jwtUser;
    }
}