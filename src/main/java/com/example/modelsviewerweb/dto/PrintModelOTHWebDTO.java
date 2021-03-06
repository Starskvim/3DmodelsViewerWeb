package com.example.modelsviewerweb.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PrintModelOTHWebDTO {
    private String nameModelOTH;
    private String modelName;
    private String fileClass;
    private String modelOTHFormat;
    private Double sizeOTH;
    private String previewOth;

    @Override
    public String toString() {
        return "ModelOTHWebDTO{" +
                "nameModelOTH='" + nameModelOTH + '\'' +
                ", modelName='" + modelName + '\'' +
                ", fileClass='" + fileClass + '\'' +
                ", modelOTHFormat='" + modelOTHFormat + '\'' +
                ", sizeOTH=" + sizeOTH +
                ", previewOth='" + previewOth + '\'' +
                '}';
    }
}
