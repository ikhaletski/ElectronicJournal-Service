package com.iba.electronicjournalservice.dto.UserResponseDto;

import com.iba.electronicjournalservice.model.user.User;
import lombok.Data;

@Data
public class UserResponseDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Long classId;
    private String role;

    public UserResponseDto(Long id,
                           String firstName,
                           String lastName,
                           String email,
                           String phone,
                           String role,
                           Long classId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.classId = classId;
    }
}
