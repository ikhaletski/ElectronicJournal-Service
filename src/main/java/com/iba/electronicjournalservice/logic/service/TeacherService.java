package com.iba.electronicjournalservice.logic.service;

import com.iba.electronicjournalservice.model.User;
import com.iba.electronicjournalservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TeacherService {

    private UserRepository userRepository;


    public List<User> findAllTeachers() {
        return userRepository.findUsersByRoleId(2L);
    }

    public Optional<User> findTeacherById(Long id) { return userRepository.findUserByIdAndRoleId(id, 2L); }

    public List<User> findTeachersByClassId(Long id) { return userRepository.findUsersByClassIdAndRoleId(id,2L); }

    public boolean isExist(Long id) { return  userRepository.existsById(id); }


}
