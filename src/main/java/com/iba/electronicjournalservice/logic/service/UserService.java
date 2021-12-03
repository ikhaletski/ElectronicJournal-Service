package com.iba.electronicjournalservice.logic.service;


import com.iba.electronicjournalservice.model.user.User;
import com.iba.electronicjournalservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class UserService {

    //@Autowired
    //private BCryptPasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUserById(Long id) { userRepository.deleteById(id); }

    public boolean isExist(Long id) { return  userRepository.existsById(id); }

    public Optional<User> findUserByEmail(String email) { return userRepository.findUserByEmail(email); }

    public User updateUserById(User user, Long id) {
        //user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setId(id);
        userRepository.save(user);
        return user;
    }

    public User addUser(User user) {
        //user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return user;
    }

}
