package com.example.modelsviewerweb.services;

import com.example.modelsviewerweb.controllers.exceptions.ModelNotFoundException;
import com.example.modelsviewerweb.dto.MapperAbstract;
import com.example.modelsviewerweb.dto.PrintModelPreviewDto;
import com.example.modelsviewerweb.dto.PrintModelWebDTO;
import com.example.modelsviewerweb.entities.PrintModelOthWeb;
import com.example.modelsviewerweb.entities.PrintModelWeb;
import com.example.modelsviewerweb.repositories.ModelRepositoryJPA;
import com.example.modelsviewerweb.repositories.ModelRepositoryTagsJPA;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PrintModelService {

    private final ModelRepositoryJPA modelRepositoryJPA;
    private final ModelRepositoryTagsJPA modelRepositoryTagsJPA;
    private final MapperAbstract mapperAbstract;
    private final SyncAppService syncAppService;


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

    @Transactional
    public void updatePreviewModel(Long id, String newPreviewName) {
        PrintModelWeb printModel = getById(id);
        for (PrintModelOthWeb printModelOthWeb: printModel.getModelOthList()){
            if(printModelOthWeb.getOthName().equals(newPreviewName)){
                printModel.setPreviewModel(printModelOthWeb);
                System.out.println(" ok ");
                break;
            }
        }
        modelRepositoryJPA.save(printModel);
    }

    public List<PrintModelPreviewDto> createPreviewDto(List<PrintModelWeb> pageModels) {
        return mapperAbstract.toPrintModelsPreviewDto(pageModels);
    }

    public void addNewModel(PrintModelWebDTO inputModel) {
        syncAppService.addNewModel(inputModel);
    }

    public void deleteModelById(Long id) {
        modelRepositoryJPA.deleteById(id);
    }

    public List<String> getAllTagsName() {
        return modelRepositoryTagsJPA.getAllNameTags();
    }

    public Page<PrintModelWeb> getAllModelByTagService(String tag, Pageable pageable){
        return modelRepositoryJPA.findAllByModelTags_NameTagContaining(tag, pageable);
    }
}
