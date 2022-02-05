package com.example.modelsviewerweb.services;

import com.example.modelsviewerweb.dto.PrintModelOTHWebDTO;
import com.example.modelsviewerweb.dto.PrintModelWebDTO;
import com.example.modelsviewerweb.entities.PrintModelOthWeb;
import com.example.modelsviewerweb.entities.PrintModelTagWeb;
import com.example.modelsviewerweb.entities.PrintModelWeb;
import com.example.modelsviewerweb.repositories.ModelRepositoryJPA;
import com.example.modelsviewerweb.repositories.ModelRepositoryTagsJPA;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SyncAppService {


    private final ModelRepositoryJPA modelRepositoryJPA;
    private final ModelRepositoryTagsJPA modelRepositoryTagsJPA;

    private static List<PrintModelWeb> printModelWebToSaveList;
    private static List<PrintModelTagWeb> printModelTagWebToSaveList;

    private static Set<PrintModelTagWeb> printModelTagWebSavedSet;
    private static Map<String, PrintModelTagWeb> assignTagMap;

    public void addNewModel(PrintModelWebDTO printModelWebDTO) {

        PrintModelWeb printModelWeb = new PrintModelWeb();

        printModelWeb.setModelName(printModelWebDTO.getModelName());
        printModelWeb.setModelSize(printModelWebDTO.getModelSize());
        printModelWeb.setModelCategory(printModelWebDTO.getModelCategory());
        printModelWeb.setModelSize(printModelWebDTO.getModelSize());
        printModelWeb.setModelPath(printModelWebDTO.getModelPath());
        printModelWeb.setViews(0L);
        detectAddAndCreateTags(printModelWebDTO, printModelWeb);
        addOthObj(printModelWebDTO, printModelWeb);

        saveNewModel();
    }


    private void detectAddAndCreateTags(PrintModelWebDTO printModelWebDTO, PrintModelWeb printModelWeb) {
        prepareDetectTags();

        Collection<String> tagsDTO = printModelWebDTO.getModelTagsNames();

        for(String tag: tagsDTO){
            if(assignTagMap.containsKey(tag)){
                PrintModelTagWeb currentTag = assignTagMap.get(tag);
                currentTag.getPrintModels().add(printModelWeb);
                printModelWeb.getModelTags().add(currentTag);
                printModelTagWebToSaveList.add(currentTag);
            } else {
                PrintModelTagWeb tagObj = new PrintModelTagWeb();
                tagObj.getPrintModels().add(printModelWeb);
                printModelWeb.getModelTags().add(tagObj);
                printModelTagWebToSaveList.add(tagObj);
            }
        }
    }

    public void prepareDetectTags(){
        printModelTagWebSavedSet.addAll(modelRepositoryTagsJPA.findAll());
        assignTagMap = printModelTagWebSavedSet.stream()
                .collect(Collectors.toConcurrentMap(PrintModelTagWeb::getNameTag, Function.identity()));
    }

    private void addOthObj(PrintModelWebDTO printModelWebDTO, PrintModelWeb printModelWeb) {

        Collection<PrintModelOTHWebDTO> inputOthList = printModelWebDTO.getModelOTHList();

        for (PrintModelOTHWebDTO othWebDTO: inputOthList){
            PrintModelOthWeb newOth = new PrintModelOthWeb();
            newOth.setOthName(othWebDTO.getNameModelOTH());
            newOth.setOthSize(othWebDTO.getSizeOTH());
            newOth.setPreviewOth(othWebDTO.getPreviewOth());
            printModelWeb.getModelOthList().add(newOth);
        }

        if (!printModelWeb.getModelOthList().isEmpty()) {
            printModelWeb.setPreviewModel(printModelWeb.getModelOthList().get(0));
        }
    }

    public void saveNewModel(){
        modelRepositoryTagsJPA.saveAll(printModelTagWebToSaveList);
    }


}
