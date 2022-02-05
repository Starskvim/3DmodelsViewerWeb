package com.example.modelsviewerweb.controllers;


import com.example.modelsviewerweb.entities.ModelOTH;
import com.example.modelsviewerweb.entities.PrintModelWeb;
import com.example.modelsviewerweb.repositories.specifications.ModelSpecs;
import com.example.modelsviewerweb.services.PrintModelService;
import com.example.modelsviewerweb.services.SerializeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collection;

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

        Specification<PrintModelWeb> spec = Specification.where(null);
        StringBuilder filters = new StringBuilder();

        if (wordName != null) {
            spec = spec.and(ModelSpecs.modelNameContains(wordName));
        }
        if (wordCategory != null) {
            spec = spec.and(ModelSpecs.modelCategoryContains(wordCategory));
            filters.append("@word-").append(wordCategory);
        }


        Page<PrintModelWeb> modelsPages = printModelService.findAllModelByPageAndSpecsService(spec, pageable);


        model.addAttribute("models", modelsPages.getContent());

        model.addAttribute("allPage", modelsPages.getTotalPages());
        model.addAttribute("filters", filters.toString());
        model.addAttribute("wordName", wordName);
        model.addAttribute("wordCategory", wordCategory);

        model.addAttribute("currentPage", pageable.getPageNumber());

        model.addAttribute("pageNumbers", printModelService.preparePageInt(pageable.getPageNumber(), modelsPages.getTotalPages()));
        return "models";
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

        PrintModelWeb printModel = printModelService.getById(id);

        Collection<ModelOTH> printModelOTHList = null; // TODO


        model.addAttribute("printModelOTHList", printModelOTHList);

        model.addAttribute("printModel", printModel);


        return "modelPage";
    }


    @PostMapping("/search_name")
    public String searchByNameController(Model model, @ModelAttribute(value = "word") String word) {

        model.addAttribute("word", word);
        model.addAttribute("models", printModelService.searchByModelNameService(word, 0));
        return "models";
    }

}
