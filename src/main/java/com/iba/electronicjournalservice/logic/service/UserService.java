package com.iba.electronicjournalservice.logic.service;


import com.iba.electronicjournalservice.dto.UserDto;
import com.iba.electronicjournalservice.dto.UserResponseDto.UserResponseDto;
import com.iba.electronicjournalservice.dto.UserResponseDto.UserResponseDtoFactory;
import com.iba.electronicjournalservice.model.user.User;
import com.iba.electronicjournalservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class UserService {

    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    public List<UserResponseDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponseDto> usersDto = new ArrayList<>();
        users.forEach(user -> {usersDto.add(UserResponseDtoFactory.fromUserToUserResponseDto(user));});
        return usersDto;
    }

    public void deleteUserById(Long id) { userRepository.deleteById(id); }

    public boolean isExist(Long id) { return  userRepository.existsById(id); }

    public Optional<User> findUserByEmail(String email) { return userRepository.findUserByEmail(email); }

    public User updateUserById(User user, Long id) {
        user.setId(id);
        userRepository.save(user);
        return user;
    }

    public User addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return user;
    }

}
