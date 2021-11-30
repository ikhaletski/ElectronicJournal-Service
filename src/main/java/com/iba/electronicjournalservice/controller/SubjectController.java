package com.iba.electronicjournalservice.controller;

import com.iba.electronicjournalservice.logic.service.SubjectService;
import com.iba.electronicjournalservice.model.Subject;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping("/subject")
@AllArgsConstructor
public class SubjectController {

    private SubjectService subjectService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Subject> findSubjectById(@PathVariable Long id) {
        Optional<Subject> subject = subjectService.findSubjectById(id);
        return subject.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }

    @GetMapping(value = "/classId/{id}")
    public ResponseEntity<List<Subject>> findSubjectsByClassId(@PathVariable Long id) {
        List<Subject> subjects = subjectService.findAllSubjectsByClassId(id);
        return !subjects.isEmpty() ? ResponseEntity.ok(subjects) : ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/teacherId/{id}")
    public ResponseEntity<List<Subject>> findSubjectsByTeacherId(@PathVariable Long id) {
        List<Subject> subjects = subjectService.findAllSubjectsByTeacherId(id);
        return !subjects.isEmpty() ? ResponseEntity.ok(subjects) : ResponseEntity.noContent().build();
    }

    @GetMapping(value = "")
    public ResponseEntity<List<Subject>> findAllSubjects() {
        List<Subject> subjects = subjectService.findAllSubjects();
        return !subjects.isEmpty() ? ResponseEntity.ok(subjects) : ResponseEntity.noContent().build();
    }

    @PostMapping(value = "")
    public ResponseEntity<Subject> addSubject(@RequestBody Optional<Subject> subject) {
        if (subject.isPresent()) {
            Subject subjectToReturn = subjectService.addSubject(subject.get());
            return ResponseEntity.ok(subjectToReturn);
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Subject> deleteSubject(@PathVariable Long id) {
        if(!subjectService.isExist(id)) return ResponseEntity.notFound().build();
        subjectService.deleteSubjectById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Subject> updateSubject(@PathVariable Long id, @RequestBody Optional<Subject> subject) {
        if (!subjectService.isExist(id)) return ResponseEntity.notFound().build();
        if (subject.isPresent()) {
            Subject subjectToReturn = subjectService.updateSubjectById(subject.get(), id);
            return ResponseEntity.ok(subjectToReturn);
        }
        return ResponseEntity.noContent().build();
    }
}
