package com.example.modelsviewerweb.repositories;

import com.example.modelsviewerweb.entities.PrintModelOthWeb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ModelRepositoryOTHJPA extends JpaRepository<PrintModelOthWeb, Long> {

}
