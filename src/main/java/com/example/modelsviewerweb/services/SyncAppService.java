package com.example.modelsviewerweb.services;

import com.example.modelsviewerweb.dto.PrintModelOTHWebDTO;
import com.example.modelsviewerweb.dto.PrintModelWebDTO;
import com.example.modelsviewerweb.entities.PrintModelOthWeb;
import com.example.modelsviewerweb.entities.PrintModelTagWeb;
import com.example.modelsviewerweb.entities.PrintModelWeb;
import com.example.modelsviewerweb.repositories.ModelRepositoryJPA;
import com.example.modelsviewerweb.repositories.ModelRepositoryTagsJPA;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SyncAppService {


    private final ModelRepositoryJPA modelRepositoryJPA;
    private final ModelRepositoryTagsJPA modelRepositoryTagsJPA;

    private static List<PrintModelWeb> printModelWebToSaveList;
    private static List<PrintModelTagWeb> printModelTagWebToSaveList;

    private static Map<String, PrintModelTagWeb> assignTagMap;

    public void addNewModel(PrintModelWebDTO printModelWebDTO) {

        PrintModelWeb printModelWeb = new PrintModelWeb();


        printModelWeb.setModelName(printModelWebDTO.getModelName());
        log.info("1 get");
        printModelWeb.setModelSize(printModelWebDTO.getModelSize());
        log.info("2 get");
        printModelWeb.setModelCategory(printModelWebDTO.getModelCategory());
        log.info("3 get");
        printModelWeb.setModelSize(printModelWebDTO.getModelSize());
        log.info("4 get");
        printModelWeb.setModelPath(printModelWebDTO.getModelPath());
        log.info("5 get");
        printModelWeb.setViews(0L);
        log.info("6 get");
        detectAddAndCreateTags(printModelWebDTO, printModelWeb);
        log.info("7 get");
        addOthObj(printModelWebDTO, printModelWeb);

        printModelWebToSaveList.add(printModelWeb);

        saveNewModel();
    }


    private void detectAddAndCreateTags(PrintModelWebDTO printModelWebDTO, PrintModelWeb printModelWeb) {

        prepareDetectTags();
        log.info("8 get");
        Collection<String> tagsDTO = printModelWebDTO.getModelTagsNames();
        log.info(printModelWebDTO.getModelTagsNames().toString());
        log.info("9 get");

        for (String tag : tagsDTO) {
            if (assignTagMap.containsKey(tag)) {
                PrintModelTagWeb currentTag = assignTagMap.get(tag);
                currentTag.getPrintModels().add(printModelWeb);
                printModelWeb.getModelTags().add(currentTag);
                printModelTagWebToSaveList.add(currentTag);
            } else {
                PrintModelTagWeb tagObj = new PrintModelTagWeb();
                tagObj.setNameTag(tag);
                tagObj.getPrintModels().add(printModelWeb);
                printModelWeb.getModelTags().add(tagObj);
                printModelTagWebToSaveList.add(tagObj);
                assignTagMap.put(tag, tagObj);
            }
        }
    }

    public void prepareDetectTags() {
        printModelWebToSaveList = new ArrayList<>();
        printModelTagWebToSaveList = new ArrayList<>();
        assignTagMap = new ConcurrentHashMap<>();
        Set<PrintModelTagWeb> printModelTagWebSavedSet = new HashSet<>(modelRepositoryTagsJPA.findAll());

        assignTagMap = printModelTagWebSavedSet.stream()
                .collect(Collectors.toConcurrentMap(PrintModelTagWeb::getNameTag, Function.identity()));
    }

    private void addOthObj(PrintModelWebDTO printModelWebDTO, PrintModelWeb printModelWeb) {

        Collection<PrintModelOTHWebDTO> inputOthList = printModelWebDTO.getModelOTHList();

        for (PrintModelOTHWebDTO othWebDTO : inputOthList) {
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

    public void saveNewModel() {
        modelRepositoryTagsJPA.saveAll(printModelTagWebToSaveList);
        modelRepositoryJPA.saveAll(printModelWebToSaveList);
    }


}
