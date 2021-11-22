package com.example.police.repo;

import com.example.police.models.Statement;
import com.example.police.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StatementRepository extends CrudRepository<Statement, Long> {
    Statement findById(int id);
    List<Statement> findByUser(User user);
    List<Statement> findByText(String text);
}