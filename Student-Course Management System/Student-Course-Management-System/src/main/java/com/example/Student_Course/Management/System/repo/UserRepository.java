package com.example.Student_Course.Management.System.repo;


import com.example.Student_Course.Management.System.data.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
