package com.example.modelsviewerweb.repositories;

import com.example.modelsviewerweb.entities.PrintModel;
import com.example.modelsviewerweb.entities.PrintModelWeb;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ModelRepositoryJPA extends JpaRepository<PrintModelWeb, Long>, JpaSpecificationExecutor<PrintModelWeb> {

    Page<PrintModelWeb> findAllBymodelNameLikeIgnoreCase(String name, Pageable page);

    @Query("Select modelName from PrintModelWeb")
    List<String> getAllNameModel();

    PrintModelWeb getByModelName(String nameModel);

    void deleteAllByModelNameIn(Collection<String> deletModelSet);



}
