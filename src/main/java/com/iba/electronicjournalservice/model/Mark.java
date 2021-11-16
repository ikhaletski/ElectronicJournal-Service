package com.iba.electronicjournalservice.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "mark")
@Data
public class Mark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long studentId;
    private long subjectId;
    private int mark;
    private Date date;
}
