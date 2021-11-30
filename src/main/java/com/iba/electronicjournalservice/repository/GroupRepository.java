package com.iba.electronicjournalservice.repository;

import com.iba.electronicjournalservice.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {

    List<Group> findGroupsByTeacherId(Long id);
}
