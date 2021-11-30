package com.iba.electronicjournalservice.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "class")
@Data
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String className;
    private Long teacherId;
}
