package com.example.modelsviewerweb.services;

import com.example.modelsviewerweb.controllers.exceptions.ModelNotFoundException;
import com.example.modelsviewerweb.entities.ModelZIP;
import com.example.modelsviewerweb.entities.PrintModel;
import com.example.modelsviewerweb.entities.PrintModelWeb;
import com.example.modelsviewerweb.repositories.ModelRepositoryJPA;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PrintModelService {

    private final ModelRepositoryJPA modelRepositoryJPA;

    public List<PrintModelWeb> getAllModelListService(){
        return modelRepositoryJPA.findAll();
    }


    public Page<PrintModelWeb> findAllModelByPageAndSpecsService(Specification<PrintModelWeb> modelSpecification, Pageable pageable){
        return modelRepositoryJPA.findAll(modelSpecification, pageable);
    }

    public PrintModelWeb getById (Long id) {
        return modelRepositoryJPA.findById(id).orElseThrow(() -> new ModelNotFoundException(id));
    }


    public List<PrintModelWeb> searchByModelNameService (String word, int page) {
        return modelRepositoryJPA.findAllBymodelNameLikeIgnoreCase(word, PageRequest.of(page, 50)).toList();
    }

    public List<Integer> preparePageInt(int current, int totalPages) {

        List<Integer> pageNumbers = new ArrayList<>();

        int start = Math.max(current - 3, 0);
        int end = Math.min(totalPages, start + 9);
        pageNumbers.add(0);
        for (int i = start; i < end; i++) {
            if (i != 0 && i != totalPages - 1) {
                pageNumbers.add(i);
            }
        }
        pageNumbers.add(totalPages - 1);
        return pageNumbers;
    }


}
