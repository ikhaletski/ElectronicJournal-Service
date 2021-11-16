package com.iba.electronicjournalservice.logic.service;


import com.iba.electronicjournalservice.model.User;
import com.iba.electronicjournalservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class UserService {

    UserRepository userRepository;

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User addUser(User user) {
        userRepository.save(user);
        return user;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public User updateUserById(User user, Long id) {
        user.setId(id);
        userRepository.save(user);
        return user;
    }

    public boolean isExist(Long id) {
       return  userRepository.existsById(id);
    }
}
