package com.iba.electronicjournalservice.controller;


import com.iba.electronicjournalservice.logic.service.GroupService;
import com.iba.electronicjournalservice.model.Group;
import com.iba.electronicjournalservice.model.Mark;
import com.iba.electronicjournalservice.model.user.User;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping("/group")
@AllArgsConstructor
public class GroupController {

    GroupService groupService;

    @GetMapping(value = "")
    public ResponseEntity<List<Group>> findAll() {
        List<Group> groups = groupService.findAll();
        return !groups.isEmpty() ? ResponseEntity.ok(groups) : ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Group> findGroupById(@PathVariable Long id) {
        Optional<Group> group = groupService.findById(id);
        return group.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }

    @GetMapping(value = "/teacherId/{id}")
    public ResponseEntity<List<Group>> findGroupsByTeacherId(@PathVariable Long id) {
        List<Group> groups = groupService.findGroupsByTeacherId(id);
        return !groups.isEmpty() ? ResponseEntity.ok(groups) : ResponseEntity.noContent().build();
    }

    @PostMapping(value = "")
    public ResponseEntity<Group> addMark(@RequestBody Optional<Group> group) {
        if (group.isPresent()) {
            groupService.addGroup(group.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Group> deleteGroup(@PathVariable Long id) {
        if(!groupService.isExist(id)) return ResponseEntity.notFound().build();
        groupService.deleteGroup(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Group> updateGroup(@PathVariable Long id, @RequestBody Optional<Group> group) {
        if (!groupService.isExist(id)) return ResponseEntity.notFound().build();
        if (group.isPresent()) {
            groupService.updateMark(id, group.get());
            return ResponseEntity.ok(group.get());
        }
        return ResponseEntity.noContent().build();
    }
}
