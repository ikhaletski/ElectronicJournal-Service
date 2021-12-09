package com.iba.electronicjournalservice.controller;

import com.iba.electronicjournalservice.dto.UserResponseDto.UserResponseDto;
import com.iba.electronicjournalservice.dto.UserResponseDto.UserResponseDtoFactory;
import com.iba.electronicjournalservice.logic.service.TeacherService;
import com.iba.electronicjournalservice.model.user.User;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teacher")
@AllArgsConstructor
public class TeacherController {

    private TeacherService teacherService;

    @GetMapping(value = "")
    public ResponseEntity<List<UserResponseDto>> findAllTeachers() {
        List<User> teachers = teacherService.findAllTeachers();
        List<UserResponseDto> teachersDto = new ArrayList<>();
        teachers.forEach(user -> {teachersDto.add(UserResponseDtoFactory.fromUserToUserResponseDto(user));});
        return !teachersDto.isEmpty() ? ResponseEntity.ok(teachersDto) : ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponseDto> findTeacherById(@PathVariable Long id) {
        Optional<User> teacher = teacherService.findTeacherById(id);
        return teacher.map(user -> ResponseEntity.ok(UserResponseDtoFactory.fromUserToUserResponseDto(user))).orElseGet(() -> ResponseEntity.noContent().build());
    }

    @GetMapping(value = "/class/{classId}")
    public ResponseEntity<List<UserResponseDto>> findTeacherByClassId(@PathVariable Long classId) {
        List<User> teachers = teacherService.findTeachersByClassId(classId);
        List<UserResponseDto> teachersDto = new ArrayList<>();
        teachers.forEach(user -> {teachersDto.add(UserResponseDtoFactory.fromUserToUserResponseDto(user));});
        return !teachersDto.isEmpty() ? ResponseEntity.ok(teachersDto) : ResponseEntity.noContent().build();
    }
}
