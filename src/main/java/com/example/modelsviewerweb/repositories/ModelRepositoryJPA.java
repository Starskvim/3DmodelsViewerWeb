package com.example.modelsviewerweb.repositories;

import com.example.modelsviewerweb.entities.PrintModelWeb;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface ModelRepositoryJPA extends JpaRepository<PrintModelWeb, Long>, JpaSpecificationExecutor<PrintModelWeb> {

    // actual
    Page<PrintModelWeb> findAllBymodelNameLikeIgnoreCase(String name, Pageable page);

    @EntityGraph(value = "ForPrintModelPage-oth", type = EntityGraph.EntityGraphType.LOAD)
    Optional<PrintModelWeb> findById(Long id);

    @Transactional
    Page<PrintModelWeb> findAllByModelTags_NameTagContaining(String nameTag, Pageable pageable); // TODO bad

    @Query("Select modelName from PrintModelWeb")
    List<String> getAllNameModel();

    // old
    PrintModelWeb getByModelName(String nameModel);

    void deleteAllByModelNameIn(Collection<String> deletModelSet);



}
