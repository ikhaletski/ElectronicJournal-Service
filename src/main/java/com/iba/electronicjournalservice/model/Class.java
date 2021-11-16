package com.iba.electronicjournalservice.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "class")
@Data
public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private long teacherId;
}
