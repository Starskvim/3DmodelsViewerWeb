package com.example.modelsviewerweb.dto;

import com.example.modelsviewerweb.entities.PrintModelWeb;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


import java.util.List;

@Mapper
public abstract class MapperAbstract {

    public abstract List<PrintModelPreviewDto> toPrintModelsPreviewDto(List<PrintModelWeb> printModelWeb);

    @Mapping(target = "previewOth",  source = "printModelWeb.previewModel.previewOth")
    public abstract PrintModelPreviewDto toPrintModelsPreviewDto(PrintModelWeb printModelWeb);

}
