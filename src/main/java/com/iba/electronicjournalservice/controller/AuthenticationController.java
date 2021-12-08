package com.iba.electronicjournalservice.controller;

import com.iba.electronicjournalservice.dto.AuthenticationRequestDto;
import com.iba.electronicjournalservice.dto.RoleDto;
import com.iba.electronicjournalservice.dto.UserDto;
import com.iba.electronicjournalservice.dto.UserResponseDto;
import com.iba.electronicjournalservice.logic.service.GroupService;
import com.iba.electronicjournalservice.logic.service.UserService;
import com.iba.electronicjournalservice.model.user.User;
import com.iba.electronicjournalservice.security.jwt.JwtTokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthenticationController {

    private UserService userService;
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;


    @PostMapping(value = "/register")
    public ResponseEntity<User> register(@RequestBody Optional<UserDto> userDto) {
        if (userDto.isPresent()) {
            User userToReturn = userService.addUser(userDto.get().toUser());
            return ResponseEntity.ok(userToReturn);
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto) {
        try{
            String username = requestDto.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            Optional<User> user = userService.findUserByEmail(username);

            if (user.isEmpty()) throw new UsernameNotFoundException("User with username " + username + " not found");

            String token = jwtTokenProvider.createToken(user.get());

            Map<Object, Object> response = new HashMap<>();
            response.put("id", user.get().getId());
            response.put("token", token);

            return ResponseEntity.ok(response);
        }catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid login or password");
        }
    }
}
