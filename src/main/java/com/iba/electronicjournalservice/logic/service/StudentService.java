package com.iba.electronicjournalservice.logic.service;

import com.iba.electronicjournalservice.model.user.Roles;
import com.iba.electronicjournalservice.model.user.User;
import com.iba.electronicjournalservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentService {
    private UserRepository userRepository;


    public List<User> findAllStudents() { return userRepository.findUsersByRole(Roles.STUDENT.name()); }

    public Optional<User> findStudentById(Long id) { return userRepository.findUserByIdAndRole(id, Roles.STUDENT.name()); }

    public List<User> findStudentsByClassId(Long id) {return userRepository.findUsersByClassIdAndRole(id,Roles.STUDENT.name()); }

    public boolean isExist(Long id) { return  userRepository.existsById(id); }


}
