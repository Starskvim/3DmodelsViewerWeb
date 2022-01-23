package com.example.modelsviewerweb.repositories;

import com.example.modelsviewerweb.entities.ModelOTH;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelRepositoryOTHJPA extends JpaRepository<ModelOTH, Long> {

    @Query("Select nameModelOTH from ModelOTH ")
    List<String> getAllnameModelOTH();

    @Query("Select modelOTHAdress from ModelOTH ")
    List<String> getAllmodelOTHAdress();

    //ModelOTH getBymodelOTHAdress();

    ModelOTH getModelOTHByModelOTHAdress(String adress);

}
