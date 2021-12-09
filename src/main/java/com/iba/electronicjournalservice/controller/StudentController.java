package com.iba.electronicjournalservice.controller;


import com.iba.electronicjournalservice.dto.UserResponseDto.UserResponseDto;
import com.iba.electronicjournalservice.dto.UserResponseDto.UserResponseDtoFactory;
import com.iba.electronicjournalservice.logic.service.StudentService;
import com.iba.electronicjournalservice.model.user.User;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
@AllArgsConstructor
public class StudentController {

    private StudentService studentService;

    @GetMapping(value = "")
    public ResponseEntity<List<UserResponseDto>> findAllStudents() {
        List<User> students = studentService.findAllStudents();
        List<UserResponseDto> studentsDto = new ArrayList<>();
        students.forEach(user -> {studentsDto.add(UserResponseDtoFactory.fromUserToUserResponseDto(user));});
        return !studentsDto.isEmpty() ? ResponseEntity.ok(studentsDto) : ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponseDto> findStudentById(@PathVariable Long id) {
        Optional<User> student = studentService.findStudentById(id);
        return student.map(user -> ResponseEntity.ok(UserResponseDtoFactory.fromUserToUserResponseDto(user))).orElseGet(() -> ResponseEntity.noContent().build());
    }

    @GetMapping(value = "/class/{classId}")
    public ResponseEntity<List<User>> findStudentsByClassId(@PathVariable Long classId) {
        List<User> students = studentService.findStudentsByClassId(classId);
        List<UserResponseDto> studentsDto = new ArrayList<>();
        students.forEach(user -> {studentsDto.add(UserResponseDtoFactory.fromUserToUserResponseDto(user));});
        return !studentsDto.isEmpty() ? ResponseEntity.ok(students) : ResponseEntity.noContent().build();
    }
}
