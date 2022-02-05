package com.example.modelsviewerweb.services;

import com.example.modelsviewerweb.entities.ModelOTH;
import com.example.modelsviewerweb.entities.ModelZIP;
import com.example.modelsviewerweb.entities.PrintModel;
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

    private CopyOnWriteArraySet<PrintModel> printModelsToSaveList = new CopyOnWriteArraySet<>();
    private CopyOnWriteArraySet<ModelOTH> modelOTHList = new CopyOnWriteArraySet<>();
    private CopyOnWriteArraySet<ModelZIP> modelZIPList = new CopyOnWriteArraySet<>();
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

    @Transactional
    public void saveAllListToJpaRepository () {

        long start = System.currentTimeMillis();




        long start3 = System.currentTimeMillis();
        if (!modelOTHList.isEmpty()) {
            modelRepositoryOTHJPA.saveAll(modelOTHList);
        }
        long fin3 = System.currentTimeMillis();
        System.out.println("modelRepositoryOTHJPA.saveAll time - " + (fin3 - start3));

        long fin = System.currentTimeMillis();
        System.out.println("ALL SAVE saveAllListToJpaRepository time - " + (fin - start));

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
