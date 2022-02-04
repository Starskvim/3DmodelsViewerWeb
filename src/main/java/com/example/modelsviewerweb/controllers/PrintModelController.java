package com.example.modelsviewerweb.controllers;

import com.example.modelsviewerweb.dto.PrintModelDTO;
import com.example.modelsviewerweb.entities.ModelOTH;
import com.example.modelsviewerweb.entities.ModelZIP;
import com.example.modelsviewerweb.entities.PrintModel;
import com.example.modelsviewerweb.repositories.specifications.ModelSpecs;
import com.example.modelsviewerweb.services.PrintModelService;
import com.example.modelsviewerweb.services.SerializeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/models")
@RequiredArgsConstructor
public class PrintModelController {
    private final PrintModelService printModelService;

    private final SerializeService serializeService;

    @GetMapping
    public String modelsController(Model model,
                                   Pageable pageable,
                                   @RequestParam(value = "wordName", required = false) String wordName,
                                   @RequestParam(value = "wordCategory", required = false) String wordCategory

    ) {

        Specification<PrintModel> spec = Specification.where(null);
        StringBuilder filters = new StringBuilder();

        if (wordName != null) {
            spec = spec.and(ModelSpecs.modelNameContains(wordName));
        }
        if (wordCategory != null) {
            spec = spec.and(ModelSpecs.modelCategoryContains(wordCategory));
            filters.append("@word-").append(wordCategory);
        }


        Page<PrintModel> modelsPages = printModelService.findAllModelByPageAndSpecsService(spec, pageable);

        long start = System.currentTimeMillis();
        List<PrintModelDTO> resultList = null; //////

        long fin = System.currentTimeMillis();
        System.out.println("Create page "+ pageable.getPageNumber() + " Time " + (fin - start));

        model.addAttribute("models", resultList);

        model.addAttribute("allPage", modelsPages.getTotalPages());
        model.addAttribute("filters", filters.toString());
        model.addAttribute("wordName", wordName);
        model.addAttribute("wordCategory", wordCategory);

        model.addAttribute("currentPage", pageable.getPageNumber());

        model.addAttribute("pageNumbers", preparePageInt(pageable.getPageNumber(), modelsPages.getTotalPages()));
        return "models";
    }


    @GetMapping("/zipPage")
    public String showZIPListController(Model model, Pageable pageable) {

        List<ModelZIP> zipsPages = printModelService.getAllZIPListByPageService(pageable).getContent();

        model.addAttribute("zips", zipsPages);
        return "zipPage";
    }


    @GetMapping("/serialization")
    public String startSerializationController() {
        long start = System.currentTimeMillis();
        try {
            serializeService.serializeObj(printModelService.getAllModelListService());
        } catch (IOException e) {
            e.printStackTrace();
        }
        long fin = System.currentTimeMillis();
        System.out.println("startSerializationController time ser - " + (fin - start));
        return "admin";
    }

    @GetMapping("/deserialization")
    public String startDeserializationController() {
        long start = System.currentTimeMillis();
        try {
            serializeService.deserializeObj();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        long fin = System.currentTimeMillis();
        System.out.println("startSerializationController time ser - " + (fin - start));
        return "admin";
    }

    @GetMapping("/good")
    public String startGood() {
        return "good";
    }


    @GetMapping("/modelOBJ/{id}")
    public String showOneModelPage(Model model, @PathVariable(value = "id") Long id) {

        PrintModel printModel = printModelService.getById(id);

        Collection<ModelOTH> printModelOTHList = printModel.getModelOTHSet();
        Collection<ModelZIP> printModelZIPList = printModel.getModelZIPSet();



        model.addAttribute("printModelOTHList", printModelOTHList);
        model.addAttribute("printModelZIPList", printModelZIPList);
        model.addAttribute("printModel", printModel);


        return "modelPage";
    }


    @PostMapping("/search_name")
    public String searchByNameController(Model model, @ModelAttribute(value = "word") String word) {

        model.addAttribute("word", word);
        model.addAttribute("models", printModelService.searchByModelNameService(word, 0));
        return "models";
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
