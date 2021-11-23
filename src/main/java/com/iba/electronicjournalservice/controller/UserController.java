package com.iba.electronicjournalservice.controller;

import com.iba.electronicjournalservice.logic.service.UserService;
import com.iba.electronicjournalservice.model.User;
import lombok.AllArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    UserService userService;

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
    public ResponseEntity<User> addUser(@RequestBody Optional<User> user) {
        if (user.isPresent()) {
            User u = userService.addUser(user.get());
            return ResponseEntity.ok(u);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody Optional<User> user) {
        if (!userService.isExist(id)) return ResponseEntity.notFound().build();
        if (user.isPresent()) {
            User userToReturn = userService.updateUserById(user.get(), id);
            return ResponseEntity.ok(userToReturn);
        }
        return ResponseEntity.noContent().build();
    }
}
