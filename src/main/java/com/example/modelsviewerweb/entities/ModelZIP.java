package com.example.modelsviewerweb.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "model_zip_files")
@NoArgsConstructor
@Setter
@Getter
public class ModelZIP implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameModelZIP;

    private String modelName;

    private String fileClass;

    private String modelZIPAdress;

    private String modelZIPFormat;

    private Double sizeZIP;

    private Integer archiveRatio;



    public ModelZIP (String nameModelZIP,String modelName, String modelZIPAdress, String modelZIPFormat, Double sizeZIP, int archiveRatio){
        this.nameModelZIP = nameModelZIP;
        this.modelName = modelName;
        this.fileClass = "ZIP";
        this.modelZIPAdress = modelZIPAdress;
        this.modelZIPFormat = modelZIPFormat;
        this.sizeZIP = sizeZIP;
        this.archiveRatio = archiveRatio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModelZIP modelZIP = (ModelZIP) o;
        return Double.compare(modelZIP.archiveRatio, archiveRatio) == 0
                && nameModelZIP.equals(modelZIP.nameModelZIP)
                && modelName.equals(modelZIP.modelName)
                && fileClass.equals(modelZIP.fileClass)
                && modelZIPAdress.equals(modelZIP.modelZIPAdress)
                && modelZIPFormat.equals(modelZIP.modelZIPFormat)
                && sizeZIP.equals(modelZIP.sizeZIP);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameModelZIP, modelName, fileClass, modelZIPAdress, modelZIPFormat, sizeZIP, archiveRatio);
    }
}