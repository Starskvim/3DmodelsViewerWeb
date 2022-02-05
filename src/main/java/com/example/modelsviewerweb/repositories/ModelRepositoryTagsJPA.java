package com.example.modelsviewerweb.repositories;

import com.example.modelsviewerweb.entities.PrintModelTagWeb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepositoryTagsJPA extends JpaRepository<PrintModelTagWeb, Long> {
}
