package com.example.modelsviewerweb.repositories;

import com.example.modelsviewerweb.entities.ModelZIP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelRepositoryZIPJPA extends JpaRepository<ModelZIP, Long> {

    @Query("Select nameModelZIP from ModelZIP ")
    List<String> getAllnameModelZIP();

    @Query("Select modelZIPAdress from ModelZIP ")
    List<String> getAllmodelZIPAdress();

    //ModelZIP getBymodelZIPAdress();

    ModelZIP getModelZIPByModelZIPAdress(String adress);


}
