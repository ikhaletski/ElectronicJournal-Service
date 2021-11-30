package com.iba.electronicjournalservice.controller;

import com.iba.electronicjournalservice.logic.service.TeacherService;
import com.iba.electronicjournalservice.model.user.User;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teacher")
@AllArgsConstructor
public class TeacherController {

    private TeacherService teacherService;

    @GetMapping(value = "")
    public ResponseEntity<List<User>> findAllTeachers() {
        List<User> students = teacherService.findAllTeachers();
        return !students.isEmpty() ? ResponseEntity.ok(students) : ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findTeacherById(@PathVariable Long id) {
        Optional<User> student = teacherService.findTeacherById(id);
        return student.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }

    @GetMapping(value = "/class/{classId}")
    public ResponseEntity<List<User>> findTeacherByClassId(@PathVariable Long classId) {
        List<User> students = teacherService.findTeachersByClassId(classId);
        return !students.isEmpty() ? ResponseEntity.ok(students) : ResponseEntity.noContent().build();
    }
}
