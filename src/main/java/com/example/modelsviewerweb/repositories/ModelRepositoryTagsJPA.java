package com.example.modelsviewerweb.repositories;

import com.example.modelsviewerweb.entities.PrintModelTagWeb;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ModelRepositoryTagsJPA extends JpaRepository<PrintModelTagWeb, Long> {

    @Query("Select nameTag from PrintModelTagWeb")
    List<String> getAllNameTags();

    @EntityGraph(value = "TagWithModels", type = EntityGraph.EntityGraphType.LOAD)
    List<PrintModelTagWeb> findAll();


}
