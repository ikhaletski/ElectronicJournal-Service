package com.iba.electronicjournalservice.repository;

import com.iba.electronicjournalservice.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

    List<Subject> findSubjectsByClassId(Long classId);
    List<Subject> findSubjectsByTeacherId(Long teacherId);
}
