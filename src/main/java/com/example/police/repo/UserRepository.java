package com.example.police.repo;

import com.example.police.models.Role;
import com.example.police.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, String> {
    User findByUsername(String username);
    List<User> findByRole(Role role);
}