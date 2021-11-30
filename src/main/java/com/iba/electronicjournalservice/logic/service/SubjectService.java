package com.iba.electronicjournalservice.logic.service;

import com.iba.electronicjournalservice.model.Subject;
import com.iba.electronicjournalservice.repository.SubjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SubjectService {

    private SubjectRepository subjectRepository;


    public Optional<Subject> findSubjectById(Long id) { return subjectRepository.findById(id); }

    public List<Subject> findAllSubjects() { return subjectRepository.findAll(); }

    public List<Subject> findAllSubjectsByClassId(Long id) { return subjectRepository.findSubjectsByClassId(id); }

    public List<Subject> findAllSubjectsByTeacherId(Long id) { return subjectRepository.findSubjectsByTeacherId(id); }

    public void deleteSubjectById(Long id) {
        subjectRepository.deleteById(id);
    }

    public boolean isExist(Long id) { return  subjectRepository.existsById(id); }

    public Subject updateSubjectById(Subject subject, Long id) {
        subject.setId(id);
        subjectRepository.save(subject);
        return subject;
    }

    public Subject addSubject(Subject subject) {
        subjectRepository.save(subject);
        return subject;
    }
}
