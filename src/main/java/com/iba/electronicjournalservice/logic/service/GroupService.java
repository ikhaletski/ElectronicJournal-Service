package com.iba.electronicjournalservice.logic.service;

import com.iba.electronicjournalservice.model.Group;
import com.iba.electronicjournalservice.model.Mark;
import com.iba.electronicjournalservice.repository.GroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GroupService {

    GroupRepository groupRepository;

    public List<Group> findAll() { return groupRepository.findAll(); }

    public Optional<Group> findById(Long id) { return groupRepository.findById(id); }

    public List<Group> findGroupsByTeacherId(Long id) {return groupRepository.findGroupsByTeacherId(id); }

    public void addGroup(Group group) { groupRepository.save(group); }

    public void deleteGroup(Long id) { groupRepository.deleteById(id); }

    public boolean isExist(Long id) { return groupRepository.existsById(id); }

    public void updateMark(Long id, Group group) {
        group.setId(id);
        groupRepository.save(group);
    }

}
