package com.iba.electronicjournalservice.controller;


import com.iba.electronicjournalservice.logic.service.StudentService;
import com.iba.electronicjournalservice.model.User;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
@AllArgsConstructor
public class StudentController {

    StudentService studentService;

    @GetMapping(value = "")
    public ResponseEntity<List<User>> findAllStudents() {
        List<User> students = studentService.findAllStudents();
        return !students.isEmpty() ? ResponseEntity.ok(students) : ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findStudentById(@PathVariable Long id) {
        Optional<User> student = studentService.findStudentById(id);
        return student.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }




}
