package com.example.modelsviewerweb.controllers.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DBStatsResponse {

    private Integer totalModels;

    private Integer totalOTH;

    private Integer totalTag;

    private Double totalSize;

}
