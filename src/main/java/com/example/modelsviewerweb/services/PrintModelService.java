package com.example.modelsviewerweb.services;

import com.example.modelsviewerweb.entities.ModelZIP;
import com.example.modelsviewerweb.entities.PrintModel;
import com.example.modelsviewerweb.repositories.ModelRepositoryJPA;
import com.example.modelsviewerweb.repositories.ModelRepositoryZIPJPA;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PrintModelService {

    private final ModelRepositoryJPA modelRepositoryJPA;
    private final ModelRepositoryZIPJPA modelRepositoryZIPJPA;

    public List<PrintModel> getAllModelListService(){
        return modelRepositoryJPA.findAll();
    }


    public Page<PrintModel> findAllModelByPageAndSpecsService(Specification<PrintModel> modelSpecification, Pageable pageable){
        return modelRepositoryJPA.findAll(modelSpecification, pageable);
    }

    public Page<ModelZIP> getAllZIPListByPageService(Pageable pageable){
        return modelRepositoryZIPJPA.findAll(pageable);
    }

    public PrintModel getById (Long id) {
        return modelRepositoryJPA.findById(id).orElse(null);
    }


    public List<PrintModel> searchByModelNameService (String word, int page) {
        return modelRepositoryJPA.findAllBymodelNameLikeIgnoreCase(word, PageRequest.of(page, 50)).toList();
    }


}
