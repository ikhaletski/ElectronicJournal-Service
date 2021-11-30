package com.iba.electronicjournalservice.controller;

import com.iba.electronicjournalservice.dto.UserDto;
import com.iba.electronicjournalservice.logic.service.UserService;
import com.iba.electronicjournalservice.model.user.User;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findUserById(@PathVariable Long id) {
        Optional<User> user = userService.findUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
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
    public ResponseEntity<User> updateRole(@PathVariable Long id, @RequestBody String role) {
        //if (role >= 0 && role <= 3) return ResponseEntity.badRequest().build();
        Optional<User> user = userService.findUserById(id);
        if(user.isEmpty()) return ResponseEntity.notFound().build();
        user.get().setRole(role);
        User userToReturn = userService.updateUserById(user.get(), id);
        return ResponseEntity.ok(userToReturn);
    }
}