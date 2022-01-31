package com.example.modelsviewerweb.repositories;

import com.example.modelsviewerweb.entities.security.Watcher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WatchersRepository extends JpaRepository<Watcher, Long> {
    Watcher findByEmail(String email);
}
