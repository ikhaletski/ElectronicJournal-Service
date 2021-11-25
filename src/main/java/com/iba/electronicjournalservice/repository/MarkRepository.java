package com.iba.electronicjournalservice.repository;

import com.iba.electronicjournalservice.model.Mark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MarkRepository extends JpaRepository<Mark, Long> {

    List<Mark> findMarksByStudentId(Long studentId);
    List<Mark> findMarksByStudentIdAndSubjectId(Long studentId, Long SubjectId);
}
