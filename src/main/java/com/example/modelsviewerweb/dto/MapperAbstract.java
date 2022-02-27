package com.example.modelsviewerweb.dto;

import com.example.modelsviewerweb.entities.PrintModelWeb;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public abstract class MapperAbstract {

    @Mapping(target = "previewOth", expression = "java(printModelWeb.getPreviewModel().getPreviewOth())")
    public abstract List<PrintModelPreviewDto> toPrintModelsPreviewDto(List<PrintModelWeb> printModelWeb);

}
