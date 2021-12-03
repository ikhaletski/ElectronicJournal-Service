package com.iba.electronicjournalservice.model.user;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="user", schema = "public")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String role = Roles.USER.name();
    private Long classId;
    private String password;
}