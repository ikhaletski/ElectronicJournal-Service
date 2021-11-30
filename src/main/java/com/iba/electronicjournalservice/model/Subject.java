package com.iba.electronicjournalservice.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="subject", schema="public")
@Data
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String subjectName;
    private Long teacherId;
    private Long classId;
}
