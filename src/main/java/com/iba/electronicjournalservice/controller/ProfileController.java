package com.iba.electronicjournalservice.controller;

import com.iba.electronicjournalservice.dto.UserResponseDto.UserResponseDto;
import com.iba.electronicjournalservice.dto.UserResponseDto.UserResponseDtoFactory;
import com.iba.electronicjournalservice.logic.service.GroupService;
import com.iba.electronicjournalservice.logic.service.UserService;
import com.iba.electronicjournalservice.model.user.User;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/profile")
@AllArgsConstructor
public class ProfileController {

    private UserService userService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponseDto> findUserById(@PathVariable Long id) {
        Optional<User> user = userService.findUserById(id);
        UserResponseDto userResponseDto = UserResponseDtoFactory.fromUserToUserResponseDto(user.get());
        //userResponseDto.setClassName(groupService.findById(user.get().getClassId()).get().getGroupName());
        return ResponseEntity.ok(userResponseDto);
    }
}
