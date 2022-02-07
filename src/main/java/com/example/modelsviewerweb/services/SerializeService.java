package com.example.modelsviewerweb.services;

import com.example.modelsviewerweb.dto.PrintModelWebDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;



@Service
@RequiredArgsConstructor
@Setter
@Getter
public class SerializeService {
    private final CollectionsService collectionsService;
    private final JsProgressBarService jsProgressBarService;
    private final SyncAppService syncAppService;
    private final ObjectMapper objectMapper;

    private static Integer total = 0;
    private static volatile int count = 0;

    @Transactional
    public void deserializeObj() throws IOException, ClassNotFoundException {

//        CopyOnWriteArraySet<PrintModel> printModelsToSaveList = collectionsService.getPrintModelsToSaveList();
//        CopyOnWriteArraySet<ModelOTH> modelOTHList = collectionsService.getModelOTHList();
//        CopyOnWriteArraySet<ModelZIP> modelZIPList = collectionsService.getModelZIPList();
//
//        Collection<File> inputSer = folderScanRepository.startScanRepository(false);
//
//        int count = 0;
//        int total = inputSer.size();
//        JsProgressBarService.setTotalCount(total);
//
//        for (File file: inputSer) {
//
//            FileInputStream fileInputStream = new FileInputStream(file.getAbsolutePath());
//            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
//            PrintModel printModel = (PrintModel) objectInputStream.readObject();
//            objectInputStream.close();
//
//            printModel.setId(0L);
//
//            for (ModelZIP modelZIP: printModel.getModelZIPSet()){
//                modelZIP.setId(0L);
//            }
//            for (ModelOTH modelOTH: printModel.getModelOTHSet()){
//                modelOTH.setId(0L);
//            }
//
//            printModelsToSaveList.add(printModel);
//
//            count ++;
//            JsProgressBarService.setCurrentCount(count);
//            JsProgressBarService.setCurrentTask(count + "/" + total + " - deser - " + printModel.getModelName());
//            System.out.println(count + "/" + total + " deserializeObj " + printModel.getModelName());
//        }
//
//        System.out.println(printModelsToSaveList.size() + " printModelsToSaveList");
//        System.out.println(modelOTHList.size() + " modelOTHList");
//        System.out.println(modelZIPList.size() + " modelZIPList");
//
//        collectionsService.saveAllListToJpaRepository();

    }

    public void deserializePrintModelWebDTO(byte[] bytes) throws IOException{

        PrintModelWebDTO printModelWebDTO = objectMapper.readValue(bytes, PrintModelWebDTO.class);
        System.out.println("4 printModelWebDTO");

//        JsProgressBarService.setCurrentTask(count + "/" + total + " - deser - " + printModelWebDTO.getModelName());
//        System.out.println(count + "/" + total + " deserializeObj " + printModelWebDTO.getModelName());

        syncAppService.addNewModel(printModelWebDTO);
        System.out.println("5 after addNewModel");
//
//        System.out.println(printModelWebDTO.getModelOTHList().size() + " size list");

    }

    public void handleFileUploadService(MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                System.out.println("1 bytes length - " + bytes.length);
                deserializePrintModelWebDTO(bytes);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Пустой файл");
        }
    }

}
