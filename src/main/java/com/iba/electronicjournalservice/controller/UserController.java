package com.iba.electronicjournalservice.controller;

import com.iba.electronicjournalservice.dto.RoleDto;
import com.iba.electronicjournalservice.dto.UserDto;
import com.iba.electronicjournalservice.dto.UserResponseDto.UserResponseDto;
import com.iba.electronicjournalservice.dto.UserResponseDto.UserResponseDtoFactory;
import com.iba.electronicjournalservice.logic.service.GroupService;
import com.iba.electronicjournalservice.logic.service.UserService;
import com.iba.electronicjournalservice.model.user.User;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.core.userdetails.UserDetailsResourceFactoryBean;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponseDto> findUserById(@PathVariable Long id) {
        Optional<User> user = userService.findUserById(id);
        UserResponseDto userResponseDto = UserResponseDtoFactory.fromUserToUserResponseDto(user.get());
        //userResponseDto.setClassName(groupService.findById(user.get().getClassId()).get().getGroupName());
        return ResponseEntity.ok(userResponseDto);
    }

    @GetMapping(value = "")
    public ResponseEntity<List<UserResponseDto>> findAllUsers() {
        List<UserResponseDto> users = userService.findAllUsers();
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
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id, @RequestBody Optional<UserDto> userDto) {
        if (!userService.isExist(id)) return ResponseEntity.notFound().build();
        User user = userService.findUserById(id).get();
        if (userDto.isPresent()) {
            User tempUser = userDto.get().toUser();
            tempUser.setRole(user.getRole());
            UserResponseDto userResponseDto = UserResponseDtoFactory.fromUserToUserResponseDto(userService.updateUserById(tempUser, id));
            return ResponseEntity.ok(userResponseDto);
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/role/{id}")
    public ResponseEntity<UserResponseDto> updateRole(@PathVariable Long id, @RequestBody RoleDto role) {
        if (!role.getRole().equals("USER") && !role.getRole().equals("ADMIN") && !role.getRole().equals("TEACHER") && !role.getRole().equals("STUDENT")) return ResponseEntity.badRequest().build();
        Optional<User> user = userService.findUserById(id);
        if(user.isEmpty()) return ResponseEntity.notFound().build();
        user.get().setRole(role.getRole());
        UserResponseDto userResponseDto = UserResponseDtoFactory.fromUserToUserResponseDto(userService.updateUserById(user.get(), id));
        return ResponseEntity.ok(userResponseDto);
    }

}