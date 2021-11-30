package com.iba.electronicjournalservice.controller;

import com.iba.electronicjournalservice.logic.service.MarkService;
import com.iba.electronicjournalservice.model.Mark;
import com.iba.electronicjournalservice.model.user.User;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mark")
@AllArgsConstructor
public class MarkController {

    private MarkService markService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Mark> findMarkById(@PathVariable Long id) {
        Optional<Mark> mark = markService.findMarkById(id);
        return mark.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }

    @GetMapping(value = "/student/{studentId}")
    public ResponseEntity<List<Mark>> findMarksByStudentId(@PathVariable Long studentId) {
        List<Mark> marks = markService.findMarksByStudentId(studentId);
        return marks.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(marks);
    }

    @GetMapping(value = "/student/{studentId}/subject/{subjectId}")
    public ResponseEntity<List<Mark>> findMarksByStudentIdAndSubjectId(@PathVariable Long studentId, @PathVariable Long subjectId) {
        List<Mark> marks = markService.findMarksByStudentIdAndSubjectId(studentId, subjectId);
        return marks.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(marks);
    }


    @PostMapping(value = "")
    public ResponseEntity<User> addMark(@RequestBody Optional<Mark> mark) {
        if (mark.isPresent()) {
            markService.addMark(mark.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Mark> deleteMark(@PathVariable Long id) {
        if(!markService.isExist(id)) return ResponseEntity.notFound().build();
        markService.deleteMark(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Mark> updateGroup(@PathVariable Long id, @RequestBody Optional<Mark> mark) {
        if (!markService.isExist(id)) return ResponseEntity.notFound().build();
        if (mark.isPresent()) {
            markService.updateMark(id, mark.get());
            return ResponseEntity.ok(mark.get());
        }
        return ResponseEntity.noContent().build();
    }

}
