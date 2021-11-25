package com.iba.electronicjournalservice.dto;

import com.iba.electronicjournalservice.model.User;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Long classId;

    public User toUser() {
        User user = new User();
        user.setId(this.id);
        user.setFirstName(this.firstName);
        user.setLastName(this.lastName);
        user.setEmail(this.email);
        user.setPhone(this.phone);
        user.setClassId(this.classId);
        return user;
    }
}
