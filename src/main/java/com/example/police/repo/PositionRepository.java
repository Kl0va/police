package com.example.police.repo;

import com.example.police.models.Position;
import org.springframework.data.repository.CrudRepository;

public interface PositionRepository extends CrudRepository<Position, String> {
    Position findByName(String name);
}