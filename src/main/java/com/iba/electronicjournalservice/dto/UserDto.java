package com.iba.electronicjournalservice.dto;

import com.iba.electronicjournalservice.model.user.User;
import lombok.Data;

@Data
public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Long classId;
    private String password;

    public User toUser() {
        User user = new User();
        user.setId(this.id);
        user.setFirstName(this.firstName);
        user.setLastName(this.lastName);
        user.setEmail(this.email);
        user.setPhone(this.phone);
        user.setClassId(this.classId);
        user.setPassword(this.password);
        return user;
    }
}
