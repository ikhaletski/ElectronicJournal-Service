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
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.isPresent() ? ResponseEntity.ok(user.get()) : ResponseEntity.noContent().build();
    }

    @GetMapping(value = "")
    public ResponseEntity<List<User>> getAllUser() {
        List<User> users = userService.getAllUsers();
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
    public ResponseEntity<User> deleteUser(@PathVariable Long id, @RequestBody Optional<User> user) {
        if (!userService.isExist(id)) return ResponseEntity.notFound().build();
        if (user.isPresent()) {
            User u = userService.updateUserById(user.get(), id);
            return ResponseEntity.ok(user.get());
        }
        return ResponseEntity.noContent().build();

    }
}
