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
public class TeacherService {

    private UserRepository userRepository;


    public List<User> findAllTeachers() {
        return userRepository.findUsersByRole(Roles.TEACHER.name());
    }

    public Optional<User> findTeacherById(Long id) { return userRepository.findUserByIdAndRole(id, Roles.TEACHER.name()); }

    public List<User> findTeachersByClassId(Long id) { return userRepository.findUsersByClassIdAndRole(id,Roles.TEACHER.name()); }

    public boolean isExist(Long id) { return  userRepository.existsById(id); }


}
