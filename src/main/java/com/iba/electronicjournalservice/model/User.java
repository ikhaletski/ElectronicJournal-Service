package com.iba.electronicjournalservice.model;

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
    private Long roleId  = 0L;
    private Long classId;
}