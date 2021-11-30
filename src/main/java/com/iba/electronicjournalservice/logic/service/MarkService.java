package com.iba.electronicjournalservice.logic.service;


import com.iba.electronicjournalservice.model.Mark;
import com.iba.electronicjournalservice.repository.MarkRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MarkService {

    private MarkRepository markRepository;

    public List<Mark> findMarksByStudentId(Long id) { return markRepository.findMarksByStudentId(id); }

    public List<Mark> findMarksByStudentIdAndSubjectId(Long studentId, Long subjectId) { return markRepository.findMarksByStudentIdAndSubjectId(studentId, subjectId); }

    public Optional<Mark> findMarkById(Long id) {return markRepository.findById(id); }

    public void addMark(Mark mark) { markRepository.save(mark); }

    public void deleteMark(Long id) { markRepository.deleteById(id); }

    public boolean isExist(Long id) { return markRepository.existsById(id); }

    public void updateMark(Long id, Mark mark) {
        mark.setId(id);
        markRepository.save(mark);
    }
}
