package com.iba.electronicjournalservice.logic.service;

import com.iba.electronicjournalservice.model.User;
import com.iba.electronicjournalservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentService {
    UserRepository userRepository;


    public List<User> findAllStudents() {
        return userRepository.findUsersByRoleId(1L);
    }

    public Optional<User> findStudentById(Long id) { return userRepository.findUserByIdAndRoleId(id, 1L); }

    public List<User> findStudentsByClassId(Long id) {return userRepository.findUsersByClassIdAndRoleId(id,1L); }

    public boolean isExist(Long id) { return  userRepository.existsById(id); }


}
