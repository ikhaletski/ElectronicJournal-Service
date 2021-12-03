package com.iba.electronicjournalservice.repository;

import com.iba.electronicjournalservice.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findUsersByRole(String role);
    Optional<User> findUserByIdAndRole(Long id, String role);
    List<User> findUsersByClassIdAndRole(Long classId, String role);
    Optional<User> findUserByEmail(String email);
}
