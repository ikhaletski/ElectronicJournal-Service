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
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private UserService userService;
    private GroupService groupService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponseDto> findUserById(@PathVariable Long id) {
        Optional<User> user = userService.findUserById(id);
        UserResponseDto userResponseDto = new UserResponseDto(user.get());
        userResponseDto.setClassName(groupService.findById(user.get().getClassId()).get().getGroupName());
        return ResponseEntity.ok(userResponseDto);
    }

    @GetMapping(value = "")
    public ResponseEntity<List<User>> findAllUsers() {
        List<User> users = userService.findAllUsers();
        return !users.isEmpty() ? ResponseEntity.ok(users) : ResponseEntity.noContent().build();
    }

    @PostMapping(value = "")
    public ResponseEntity<User> addUser(@RequestBody Optional<UserDto> userDto) {
        if (userDto.isPresent()) {
            User userToReturn = userService.addUser(userDto.get().toUser());
            return ResponseEntity.ok(userToReturn);
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        if(!userService.isExist(id)) return ResponseEntity.notFound().build();
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody Optional<UserDto> userDto) {
        if (!userService.isExist(id)) return ResponseEntity.notFound().build();
        User user = userService.findUserById(id).get();
        if (userDto.isPresent()) {
            User tempUser = userDto.get().toUser();
            tempUser.setRole(user.getRole());
            User userToReturn = userService.updateUserById(tempUser, id);
            return ResponseEntity.ok(userToReturn);
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/role/{id}")
    public ResponseEntity<User> updateRole(@PathVariable Long id, @RequestBody RoleDto role) {
        if (!role.getRole().equals("USER") && !role.getRole().equals("ADMIN") && !role.getRole().equals("TEACHER") && !role.getRole().equals("STUDENT")) return ResponseEntity.badRequest().build();
        Optional<User> user = userService.findUserById(id);
        if(user.isEmpty()) return ResponseEntity.notFound().build();
        user.get().setRole(role.getRole());
        User userToReturn = userService.updateUserById(user.get(), id);
        return ResponseEntity.ok(userToReturn);
    }

}