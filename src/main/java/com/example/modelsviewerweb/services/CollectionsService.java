package com.example.modelsviewerweb.services;

import com.example.modelsviewerweb.repositories.ModelRepositoryJPA;
import com.example.modelsviewerweb.repositories.ModelRepositoryOTHJPA;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

@Service
@RequiredArgsConstructor
@Setter
@Getter
public class CollectionsService {

    private final ModelRepositoryJPA modelRepositoryJPA;
    private final ModelRepositoryOTHJPA modelRepositoryOTHJPA;

    private CopyOnWriteArrayList<String> zipFormatList = new CopyOnWriteArrayList<>();
    private CopyOnWriteArraySet<String> printModelsToSaveNameStringSet = new CopyOnWriteArraySet<>();


    private HashSet<String> printModelsSavedNameStringSet = new HashSet<>(10000);

    //?
    private HashSet<String> printModelsSavedFilesNameStringSet = new HashSet<>(30000);

    private HashSet<String> printModelsSavedFilesAdressStringSet = new HashSet<>(30000);


    public boolean checkPrintModelsNameStringSet(String name) {
        if (printModelsToSaveNameStringSet.isEmpty()) {
            return false;
        } else return printModelsToSaveNameStringSet.contains(name);
    }

    public boolean checkPrintModelsFilesSavedNameStringSet(String name){
        if (printModelsSavedFilesNameStringSet == null){
            return true;
        }
        else if (printModelsSavedFilesNameStringSet.isEmpty()) {
            return true;
        }else return !printModelsSavedFilesNameStringSet.contains(name);
    }
}
