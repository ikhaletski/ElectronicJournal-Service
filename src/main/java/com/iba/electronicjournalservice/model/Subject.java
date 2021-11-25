package com.iba.electronicjournalservice.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "subject", schema = "public")
@Data
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private long teacherId;
    private long classId;
}
