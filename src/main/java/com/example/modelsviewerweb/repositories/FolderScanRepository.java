package com.example.modelsviewerweb.repositories;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
@Getter
@Setter
public class FolderScanRepository {

    @Value("${scan.adress1}")
    private String adress1;

    @Value("${scan.adress2}")
    private String adress2;

    @Value("${scan.adress3}")
    private String adress3;

    @Value("${scan.adressSer}")
    private String adressSer;


    public Collection<File> startScanRepository(Boolean mode) throws IOException {

        Collection<File> files;
        long start = System.currentTimeMillis();
        if(mode) {
            File input1 = new File(adress1);
            File input2 = new File(adress2);
            File input3 = new File(adress3);
            files = FileUtils.streamFiles(input1, true, null).collect(Collectors.toList());
            Collection<File> files2 = FileUtils.streamFiles(input2, true, null).collect(Collectors.toList());
            Collection<File> files3 = FileUtils.streamFiles(input3, true, null).collect(Collectors.toList());
            files.addAll(files2);
            files.addAll(files3);
        } else {
            File inpuSer = new File(adressSer);
            files = FileUtils.streamFiles(inpuSer, true, null).collect(Collectors.toList());
        }

        long fin = System.currentTimeMillis();
        System.out.println("ScanRepository SIZE " + files.size());
        System.out.println("ScanRepository TIME " + (fin - start));
        return files;
    }

}
