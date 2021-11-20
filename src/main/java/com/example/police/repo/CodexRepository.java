package com.example.police.repo;

import com.example.police.models.Codex;
import org.springframework.data.repository.CrudRepository;

public interface CodexRepository extends CrudRepository<Codex, String> {
    Codex findByName(String name);
}