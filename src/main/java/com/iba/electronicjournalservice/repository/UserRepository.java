package com.iba.electronicjournalservice.repository;

import com.iba.electronicjournalservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.management.relation.Role;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findUsersByRoleId(Long roleId);
    Optional<User> findUserByIdAndRoleId(Long id, Long roleId);
    List<User> findUsersByClassIdAndRoleId(Long classId, Long RoleId);
}
