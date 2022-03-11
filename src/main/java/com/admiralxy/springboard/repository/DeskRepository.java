package com.admiralxy.springboard.repository;

import com.admiralxy.springboard.entity.Desk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeskRepository extends JpaRepository<Desk, Long> {
    Desk findByName(String name);
}
