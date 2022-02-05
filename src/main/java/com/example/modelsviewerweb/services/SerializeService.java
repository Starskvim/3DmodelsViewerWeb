package com.example.modelsviewerweb.services;

import com.example.modelsviewerweb.dto.PrintModelWebDTO;
import com.example.modelsviewerweb.entities.PrintModel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;


@Service
@RequiredArgsConstructor
@Setter
@Getter
public class SerializeService {
    private final CollectionsService collectionsService;
    private final JsProgressBarService jsProgressBarService;
    private final SyncAppService syncAppService;

    private static Integer total = 0;
    private static volatile int  count = 0;

    @Value("${scan.adressSer}")
    private String adressSer;

    public void serializeObj(List<PrintModel> outputList) throws IOException {


        total = outputList.size();
        JsProgressBarService.setTotalCount(total);

        for (PrintModel printModel : outputList) {


            FileOutputStream outputStream = new FileOutputStream(adressSer + "/" + printModel.getModelName() +".ser");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(printModel);
            objectOutputStream.close();

            count ++;

            JsProgressBarService.setCurrentCount(count);
            JsProgressBarService.setCurrentTask(count + "/" + total + " - ser - " + printModel.getModelName());
            System.out.println(count + "/" + total + " serializeObj " + printModel.getModelName());


        }
    }

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


    public void deserializePrintModelWebDTO(byte[] bytes) throws IOException, ClassNotFoundException {

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        System.out.println("2 byteArrayInputStream");
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        System.out.println("3 objectInputStream");

        Object printModelWebDTO =  objectInputStream.readObject();

        System.out.println(printModelWebDTO.toString());

        System.out.println("4 printModelWebDTO");
        objectInputStream.close();

//        JsProgressBarService.setCurrentTask(count + "/" + total + " - deser - " + printModelWebDTO.getModelName());
//        System.out.println(count + "/" + total + " deserializeObj " + printModelWebDTO.getModelName());
//
//        syncAppService.addNewModel(printModelWebDTO);
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
